(ns aoc.day-6
  "--- Day 6: Custom Customs ---
  ...
  For each group, count the number of questions to which anyone answered
  'yes'. What is the sum of those counts?
  --- Part Two ---
  ...
  For each group, count the number of questions to which everyone answered
  'yes'. What is the sum of those counts?"
  (:require [clojure.string :as string]
            [clojure.set :as set]))

(defn group-yes
  "Identify how many unique questions were answered 'yes' in a given group."
  [group]
  (count (remove #{\newline \space} (set group))))

(defn group-all-yes
  "Identify the questions where everyone in the group answered 'yes'"
  [group]
  (let [members (map #(set %) (string/split group #"\n\s*"))]
    (count (apply set/intersection members))))
