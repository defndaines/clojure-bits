(ns aoc.day-15-test
  "Tests against the aoc.day-15 namespace."
  (:require [clojure.test :refer [deftest is testing]]
            [aoc.day-15 :as day-15]))

(def sample-input [0 3 6])

(def input [0 20 7 16 1 18 15])

(deftest game-test
  (testing "handling rounds of the number game."
    (is (= 0 (day-15/game sample-input 1)))
    (is (= 3 (day-15/game sample-input 2)))
    (is (= 6 (day-15/game sample-input 3)))
    (is (= 0 (day-15/game sample-input 4)))
    (is (= 3 (day-15/game sample-input 5)))
    (is (= 3 (day-15/game sample-input 6)))
    (is (= 1 (day-15/game sample-input 7)))
    (is (= 0 (day-15/game sample-input 8)))
    (is (= 4 (day-15/game sample-input 9)))
    (is (= 0 (day-15/game sample-input 10)))
    (is (= 436 (day-15/game sample-input 2020)))
    (is (= 1 (day-15/game [1 3 2] 2020)))
    (is (= 10 (day-15/game [2 1 3] 2020)))
    (is (= 27 (day-15/game [1 2 3] 2020)))
    (is (= 78 (day-15/game [2 3 1] 2020)))
    (is (= 438 (day-15/game [3 2 1] 2020)))
    (is (= 1836 (day-15/game [3 1 2] 2020)))
    (is (= 1025 (day-15/game input 2020)))))
