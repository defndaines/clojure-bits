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

(defn distinct-arrangments
  [s]
  (let [required (reduce (fn [acc e]
                           (if (= -3 (apply - e))
                             (conj (conj acc (first e)) (last e))
                             acc))
                         #{0} ; Outlet is always required.
                         (partition 2 1 (full-set s)))
        candidates (set/difference (set s) required)
        just-ones (filter valid-arrangement? (map #(conj required %) candidates))
        arrangements (for [n (range 2 (count candidates))]
                         (filter valid-arrangement?
                                 (map #(concat % required)
                                      (combo/combinations candidates n))))]
    (+ 1 ; The full set is always valid.
       (if (valid-arrangement? required) 1 0) ; Skip all "optional" values.
       (count just-ones) ; Combo library doesn't accept combinations of 1.
       (apply + (map count arrangements)))))
