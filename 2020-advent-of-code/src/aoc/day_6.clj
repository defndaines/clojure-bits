(ns aoc.day-6
  "--- Day 6: Custom Customs ---
  ...
  For each group, count the number of questions to which anyone answered
  'yes'. What is the sum of those counts?"
  (:require [clojure.spec.alpha :as s]))

(defn group-yes
  "Identify how many unique questions were answered 'yes' in a given group."
  [group]
  (count (remove #{\newline \space} (set group))))
