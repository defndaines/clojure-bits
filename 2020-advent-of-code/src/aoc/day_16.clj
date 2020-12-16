(ns aoc.day-16
  "--- Day 16: Ticket Translation ---
  ...
  Consider the validity of the nearby tickets you scanned. What is your ticket
  scanning error rate?"
  (:require [clojure.string :as string]))

(defn- parse-rules
  [input]
  (let [rules-section (take-while #(not= "" %) input)]
    (reduce
      (fn [acc e]
        (let [[_ a b c d] (re-find #"^.*: (\d+)-(\d+) or (\d+)-(\d+)$" e)]
          (into
            (into acc (range (Integer/parseInt a) (inc (Integer/parseInt b))))
            (range (Integer/parseInt c) (inc (Integer/parseInt d))))))
      #{}
      rules-section)))

(defn validate
  [input ticket]
  (let [rules (parse-rules input)]
    (first (remove rules ticket))))

(defn- line->ticket
  [line]
  (map #(Integer/parseInt %) (string/split line #",")))

(defn scanning-error-rate
  [input]
  (let [rules (parse-rules input)
        nearby (drop-while #(not= "nearby tickets:" %) input)]
    (->> (rest nearby)
         (map line->ticket)
         (map (partial remove rules))
         (keep first)
         (apply +))))
