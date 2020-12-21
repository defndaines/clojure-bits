(ns aoc.day-18-test
  "Tests against the aoc.day-18 namespace."
  (:require [clojure.test :refer [deftest is testing]]
            [clojure.string :as string]
            [aoc.day-18 :as day-18]))

(def input (string/split-lines (slurp "resources/day-18-input.txt")))

(deftest math-test
  (testing "math with different order of precedence."
    (is (= 71 (day-18/math "1 + 2 * 3 + 4 * 5 + 6")))
    (is (= 51 (day-18/math "1 + (2 * 3) + (4 * (5 + 6))")))
    (is (= 26 (day-18/math "2 * 3 + (4 * 5)")))
    (is (= 437 (day-18/math "5 + (8 * 3 + 9 + 3 * 4 * 3)")))
    (is (= 12240 (day-18/math "5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))")))
    (is (= 13632 (day-18/math "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2")))
    (is (= 4696493914530 (reduce + (map day-18/math input))))))
