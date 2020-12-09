(ns aoc.core
  (:require [clojure.string :as string]))

(defn input->numbers
  "Convert a file where each line is a number into a sequence of numbers"
  [filename]
  (->> filename
       slurp
       string/split-lines
       (map #(Long/parseLong %))))
