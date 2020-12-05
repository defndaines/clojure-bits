(ns aoc.day-5
  "--- Day 5: Binary Boarding ---
  ...
  As a sanity check, look through your list of boarding passes. What is the
  highest seat ID on a boarding pass?"
  (:require [clojure.string :as string]))

(defn seat-id
  "Derive seat ID from boarding pass."
  [bp]
  (let [bin-str (-> bp
                    (string/replace #"[FL]" "0")
                    (string/replace #"[BR]" "1"))]
    (Integer/parseUnsignedInt bin-str 2)))
