(ns aoc.day-7
  "--- Day 7: Handy Haversacks ---
  ...
  How many bag colors can eventually contain at least one shiny gold bag?
  --- Part Two ---
  ...
  How many individual bags are required inside your single shiny gold bag?"
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

(defn- parse-rule-with-count
  [rule]
  (let [[_ outer inner] (re-find #"^(.*) bags contain (.*)\.$" rule)
        bags (map
               #(rest (re-find #"(\d+) (.*) bags?" %))
               (string/split inner #","))]
    {outer bags}))

(defn- parse-rules-with-count
  [rules]
  (reduce
    (fn [acc e] (merge acc (parse-rule-with-count e)))
    {}
    rules))

(defn bag-count
  [rules bag cnt]
  (let [inside (get rules bag)]
    (reduce
      (fn [acc [n color]]
        (if (seq n)
          (let [amt (* cnt (Integer/parseInt n))]
            (+ acc amt (bag-count rules color amt)))
          0))
      0 inside)))

(defn bag-contains
  [rules bag]
  (let [bag-rules (parse-rules-with-count rules)]
    (bag-count bag-rules bag 1)))
