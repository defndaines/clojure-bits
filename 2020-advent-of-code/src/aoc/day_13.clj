(ns aoc.day-13
  "--- Day 13: Shuttle Search ---
  ...
  What is the ID of the earliest bus you can take to the airport multiplied by
  the number of minutes you'll need to wait for that bus?
  --- Part Two ---
  ...
  What is the earliest timestamp such that all of the listed bus IDs depart at
  offsets matching their positions in the list?"
  (:require [clojure.string :as string]))

(defn from [n] (lazy-seq (cons n (from (inc n)))))

(defn- parse-buses
  [sched]
  (map #(Integer/parseInt %)
       (remove #(= "x" %)
               (string/split sched #","))))

(defn next-bus
  [sched start]
  (let [buses (parse-buses sched)]
    (first
      (keep
        (fn [ts]
          (when-let [[bus] (seq (keep #(when (zero? (mod ts %)) %) buses))]
            [bus ts]))
        (from start)))))

(defn- tick
  [buses ts]
  (lazy-seq (cons [ts (set (keep #(when (zero? (mod ts %)) %) buses))]
                  (tick buses (inc ts)))))

(defn- bus-schedule
  [sched start]
  (tick (parse-buses sched) start))

(defn- window-pred [sched]
  (map
    (fn [e]
      (if (= "x" e)
        (constantly true)
        (let [n (Integer/parseInt e)]
          (fn [s] (contains? s n)))))
    (string/split sched #",")))

(defn find-schedule
  "Find the first timestamp which matches the provided scheduling sequence."
  [sched]
  (let [jump (Integer/parseInt (first (string/split sched #",")))
        skip-fn (fn skipper [n] (lazy-seq (cons n (skipper (+ jump n)))))
        window? (window-pred sched)
        window-size (count window?)]
    (->> (skip-fn 0)
         (drop-while
           (fn [ts]
             (not-every?
               identity
               (map
                 (fn [f x] (f (second x)))
                 window?
                 (take window-size (bus-schedule sched ts))))))
         first)))
