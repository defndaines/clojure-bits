(ns aoc.day-18
  "--- Day 18: Operation Order ---
  ...
  Evaluate the expression on each line of the homework; what is the sum of the
  resulting values?
  --- Part Two ---
  ...
  What do you get if you add up the results of evaluating the homework
  problems using these new rules?"
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

(declare math')

(defn- sub'
  [expr]
  (if-let [match (re-find #"\(([^()]+)\)" expr)]
    (sub' (string/replace expr (first match) (str (math' (second match)))))
    expr))

(defn math-+
  [expr]
  (if-let [match (re-find #"(\d+) \+ (\d+)")]
    (let [sum (+ (Integer/parseInt (nth match 1)) (Integer/parseInt (nth match 2)))]
      (math' (string/replace expr (first match) (str sum))))
    expr))

(defn plus-precedence
  [expr]
  )
