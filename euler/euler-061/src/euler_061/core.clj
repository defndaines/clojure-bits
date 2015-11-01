(ns euler-061.core
  (:require [clojure.math.combinatorics :as combo]))

(defn positive-numbers
  ([] (positive-numbers 1))
  ([n] (cons n (lazy-seq (positive-numbers (inc n))))))

;; P3,n=n(n+1)/2
(defn triangle [n]
  (/ (* n (inc n)) 2))

(def triangles
  (map triangle (positive-numbers)))

;; P4,n=n2
(defn square [n]
  (* n n))

(def squares
  (map square (positive-numbers)))

;; P5,n=n(3n−1)/2
(defn pentagonal [n]
  (/ (* n (- (* 3 n) 1)) 2))

(def pentagonals
  (map pentagonal (positive-numbers)))

;; P6,n=n(2n−1)
(defn hexagonal [n]
  (* n (- (* 2 n) 1)))

(def hexagonals
  (map hexagonal (positive-numbers)))

;; P7,n=n(5n−3)/2
(defn heptagonal [n]
  (/ (* n (- (* 5 n) 3)) 2))

(def heptagonals
  (map heptagonal (positive-numbers)))

;; P8,n=n(3n−2)
(defn octagonal [n]
  (* n (- (* 3 n) 2)))

(def octagonals
  (map octagonal (positive-numbers)))

(defn digits [n]
  "Get the number of digits in a number."
  (-> n Math/log10 Math/floor int inc))

(defn four-digits [coll]
  "Pull all the four-digit numbers from a lazy sequence."
  (take-while #(= 4 (digits %))
              (drop-while #(> 4 (digits %)) coll)))

(def f-triangles (four-digits triangles))
(def f-squares (four-digits squares))
(def f-pentagonals (four-digits pentagonals))
(def f-hexagonals (four-digits hexagonals))
(def f-heptagonals (four-digits heptagonals))
(def f-octagonals (four-digits octagonals))

(defn cycle? [x y]
  (= (rem x 100) (int (Math/floor (/ y 100)))))

(defn connections [from to]
  (for [x from y to :when (cycle? (last x) y)]
    (conj x y)))

(defn coll-cycle? [coll]
  (cycle? (last coll) (first coll)))

;; Start search with octagonals, the smallest set, to reduce search space.
(defn check-permutation [perm]
  (filter coll-cycle?
          (reduce connections (map vector f-octagonals) perm)))

(def search-space
  (combo/permutations
    [f-heptagonals f-hexagonals f-pentagonals f-squares f-triangles]))

(->> (map check-permutation search-space)
     (filter seq)
     first
     first
     (reduce + ))
