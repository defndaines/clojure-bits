(ns aoc.day-7
  "--- Day 7: Handy Haversacks ---
  ...
  How many bag colors can eventually contain at least one shiny gold bag?"
  (:require [clojure.string :as string]
            [clojure.set :as set]))

(defn- parse-rule
  [rule]
  (let [[_ outer inner] (re-find #"^(.*) bags contain (.*)\.$" rule)
        bags (map
               #(second (re-find #"\d+ (.*) bags?" %))
               (string/split inner #","))]
    (zipmap bags (repeat #{outer}))))

(defn- parse-rules
  [rules]
  (reduce
    (fn [acc e] (merge-with set/union acc (parse-rule e)))
    {}
    rules))

(defn bag-options
  [rules bag]
  (let [bag-rules (parse-rules rules)]
    (loop [acc #{}
           bags #{bag}]
      (let [within (reduce
                     (fn [acc e] (set/union acc (get bag-rules e)))
                     #{}
                     bags)]
        (if (seq within)
          (recur (set/union acc within) within)
          acc)))))
