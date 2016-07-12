(ns file-ending)

(defmulti parse
  "Parse a file, dispatching off the filename extension."
  (fn [filename]
    (subs filename
          (inc (clojure.string/last-index-of filename \.)))))

(defmethod parse "csv" [filename]
  :reading-csv)

(defmethod parse "json" [filename]
  :reading-json)
