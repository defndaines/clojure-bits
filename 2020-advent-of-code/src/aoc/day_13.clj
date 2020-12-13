(ns aoc.day-13
  "--- Day 13: Shuttle Search ---
  ...
  What is the ID of the earliest bus you can take to the airport multiplied by
  the number of minutes you'll need to wait for that bus?"
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
