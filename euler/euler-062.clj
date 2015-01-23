(ns euler-062.core
  (:require [clojure.math.combinatorics :as combo]))

;; The cube, 41063625 (345^3), can be permuted to produce two other cubes:
;; 56623104 (384^3) and 66430125 (405^3). In fact, 41063625 is the smallest cube
;; which has exactly three permutations of its digits which are also cube.
;; Find the smallest cube for which exactly five permutations of its digits are
;; cube.

(defn cube [x]
  (* x x x))

(defn digits-in [x]
  (count (seq (str x))))

(defn cubes [x]
  (cons (cube x)
        (lazy-seq (cubes (inc x)))))

(defn lowest-number-by-digits [n]
  (Long/parseLong (apply str (cons \1 (repeat (dec n) \0)))))

(defn cubes-by-digits [n]
  (take-while #(= n (digits-in %)) (cubes (long (Math/ceil (Math/cbrt (lowest-number-by-digits n)))))))

(def ten-digits (cubes-by-digits 10))
 
(defn permutations-of [x]
  (into #{} (map #(Long/parseLong (apply str %)) (combo/permutations (seq (str x))))))

;; So this returns the example answer.
(filter (permutations-of (first eight-digits)) eight-digits)

(defn smallest-cube-with-permutations [n s]
  (if (seq s)
    (if (= n (count (filter (permutations-of (first s)) s)))
      (first s)
      (recur n (rest s)))))
