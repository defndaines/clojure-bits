(ns aoc.day-3-test
  "Tests against the aoc.day-3 namespace."
  (:require [clojure.test :refer [deftest is testing]]
            [clojure.string :as string]
            [aoc.day-3 :as day-3]))

(def sample-data
  ["..##......."
   "#...#...#.."
   ".#....#..#."
   "..#.#...#.#"
   ".#...##..#."
   "..#.##....."
   ".#.#.#....#"
   ".#........#"
   "#.##...#..."
   "#...##....#"
   ".#..#...#.#"])

(def input (string/split-lines (slurp "resources/day-3-input.txt")))

(deftest trees-on-slope-test
  (testing "number of trees encountered using the provided slope"
    (is (= 7 (day-3/trees-on-slope sample-data 3 1))))
  (testing "alternative slopes"
    (is (= 2 (day-3/trees-on-slope sample-data 1 1)))
    (is (= 3 (day-3/trees-on-slope sample-data 5 1)))
    (is (= 4 (day-3/trees-on-slope sample-data 7 1)))
    (is (= 2 (day-3/trees-on-slope sample-data 1 2))))
  (testing "slopes on input data"
    (is (= 63 (day-3/trees-on-slope input 1 1)))
    (is (= 181 (day-3/trees-on-slope input 3 1)))
    (is (= 55 (day-3/trees-on-slope input 5 1)))
    (is (= 67 (day-3/trees-on-slope input 7 1)))
    (is (= 30 (day-3/trees-on-slope input 1 2)))))

(deftest slopes-product-test
  (testing "product of five sample slopes"
    (is (= 336
           (*
             (day-3/trees-on-slope sample-data 1 1)
             (day-3/trees-on-slope sample-data 3 1)
             (day-3/trees-on-slope sample-data 5 1)
             (day-3/trees-on-slope sample-data 7 1)
             (day-3/trees-on-slope sample-data 1 2)))))
  (testing "product of five sample slopes"
    (is (= 1260601650
           (*
            (day-3/trees-on-slope input 1 1)
            (day-3/trees-on-slope input 3 1)
            (day-3/trees-on-slope input 5 1)
            (day-3/trees-on-slope input 7 1)
            (day-3/trees-on-slope input 1 2))))))
