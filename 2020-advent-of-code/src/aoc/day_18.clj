(ns aoc.day-18
  "--- Day 18: Operation Order ---
  ...
  Evaluate the expression on each line of the homework; what is the sum of the
  resulting values?"
  (:require [clojure.string :as string]))

(declare math)

(defn- sub-expr
  [expr]
  (if-let [match (re-find #"\(([^()]+)\)" expr)]
    (sub-expr (string/replace expr (first match) (str (math (second match)))))
    expr))

(defn math
  [expr]
  (loop [acc nil
         f nil
         bits (string/split (sub-expr expr) #"\s")]
    (let [x (first bits)]
      (case x
        nil acc
        "*" (recur acc * (rest bits))
        "+" (recur acc + (rest bits))
        (let [i (Integer/parseInt x)]
          (if (nil? f)
            (recur i f (rest bits))
            (recur (f i acc) nil (rest bits))))))))
