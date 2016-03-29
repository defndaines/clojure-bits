(ns plus-minus.core
  (:require
    [clojure.data.csv :as csv]
    [clojure.java.io :as io]
    [clojurewerkz.money.amounts :as ma]
    [clojurewerkz.money.currencies :as mc]
    [clojurewerkz.money.format :as mf])
  (:gen-class))

;; No error handling. If an assumption is incorrect, just break and fix.

;; Expects prices.csv to be in the same directory as the JAR file.
(def price-file "prices.csv")

(defn read-prices [file]
  (with-open [in-file (io/reader file)]
    (doall
      (csv/read-csv in-file))))

(defn prices
  "Reads in the prices file to get a map of product IDs to prices."
  [file]
  (into {} (read-prices file)))

(defn lines-from
  "Read in all the lines from the receipt which are relevant to the task."
  [receipt]
  (with-open [r (io/reader receipt)]
    (let [lines (transient [])]
      (doseq [line (line-seq r)]
        (cond
          (.startsWith line " ") :skip-total  ; Only TOTAL begins with space.
          (.startsWith line "***") (pop! lines)  ; Only VOID begin with asterisks.
          :else (conj! lines line)))
      (persistent! lines))))

(defn store-number
  "Get the store number from the first line of a receipt."
  [line]
  ;; For all receipts seen, the store number starts at character 14.
  (clojure.string/trim (subs line 14)))

(defn to-line-item
  "Convert a receipt line into a line item pair of product ID and price."
  [line]
  ;; Item IDs are ten digits after three spaces.
  (subvec (re-matches #".*   (\d{10})\s+([0-9\.]+) .$" line) 1))

(defn price-diff
  "Convert two string price values to a money difference."
  [expected actual]
  (apply ma/minus (map #(ma/amount-of mc/USD (Double/parseDouble %)) [actual expected])))

; To aggregate by item ID:
; (update acc item (fnil ma/plus (ma/amount-of mc/USD 0.0)) (price-diff expected price))

(defn aggregator-fn
  "Get a reduce function to aggregate price differences in a receipt."
  [prices]
  (fn [acc e]
    (let [[item price] (to-line-item e)
          expected (get prices item)]
      (if (not= price expected)
        (ma/plus acc (price-diff expected price))
        acc))))

(defn check-prices
  "Check the charges in a receipt against their expected prices."
  [line-items prices]
  (reduce (aggregator-fn prices) (ma/amount-of mc/USD 0.0) line-items))

(defn parse-receipt
  "Parse a receipt file and get a map of store number to price discrepancies."
  [file-name prices]
  (let [[store-line & line-items] (lines-from file-name)]
    {(store-number store-line)
     (check-prices line-items prices)}))

(defn scan-directory
  "Get the store ID and price differences for each file in a given directory."
  [directory prices]
  ;; This includes the nil for the directory.
  (map #(when (.isFile %) (parse-receipt % prices))
        (file-seq (io/file directory))))

(defn agg-by-store
  "Scan the directory and aggregate by store."
  [directory prices]
  (reduce
    (fn [acc e]
      (if e
        (merge-with (fn [result latter] (ma/plus result latter)) acc e)
        acc))
    (scan-directory directory prices)))

(defn print-csv
  "Print the results to a CSV file with the name provided."
  [results file-name]
  (with-open [out (io/writer file-name)]
    (csv/write-csv
      out
      (conj
        (for [[k v] (sort-by val results)]
          [k (.replaceAll (subs (mf/format v) 1) "," "")])
        ["Store" "plus minus"]))))

(defn -main
  "Scan the provided directory and output a CSV file with the same name."
  [& args]
  (if-let [[d & _] args]
    (print-csv
      (agg-by-store d (prices price-file))
      (.replaceAll (str d ".csv") "/" ""))))
