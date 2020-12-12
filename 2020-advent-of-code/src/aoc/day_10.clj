(ns aoc.day-10
  "--- Day 10: Adapter Array ---
  ...
  What is the number of 1-jolt differences multiplied by the number of 3-jolt
  differences?
  --- Part Two ---
  ...
  What is the total number of distinct ways you can arrange the adapters to
  connect the charging outlet to your device?"
  (:require [clojure.set :as set]
            [clojure.math.combinatorics :as combo]))

(defn- full-set
  "Add in outlet, 0, and the build-in adapter, which is always 3 more than the
  highest."
  [s]
  (let [sorted (conj (sort s) 0)
        built-in (+ 3 (last sorted))]
    (conj (vec sorted) built-in)))

(defn diff-counts
  [s]
  (->> s
       full-set
       (partition 2 1)
       (map #(apply (comp - -) %))
       frequencies))

(defn- valid-arrangement?
  [s]
  (every?
    (fn [[a b]] (< (- b a) 4))
    (partition 2 1 (sort s))))

(defn- arrangements
  [s]
  (if (< 2 (count s))
    (let [sorted (sort s)
          required #{(first sorted) (last sorted)}
          candidates (butlast (rest sorted))]
      (apply
        +
        (map count
             (conj
               (for [n (range 2 (inc (count candidates)))]
                 (filter valid-arrangement?
                         (map #(concat % required)
                              (combo/combinations candidates n))))
               (filter valid-arrangement?
                       (map #(sort (conj required %))
                            candidates))
               (filter valid-arrangement? [required])))))
    1))

(defn distinct-arrangments
  [s]
  (let [sorted (vec (sort (conj s 0)))
        full (conj sorted (+ 3 (last sorted)))]
    (loop [acc 1
           batch []
           l full]
      (let [[a b] l]
        (cond
          (nil? b) acc
          (= (+ 3 a) b) (recur (* acc (arrangements (conj batch a b))) [] (rest l))
          :else (recur acc (conj batch a) (rest l)))))))
