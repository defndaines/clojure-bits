(ns aoc.core-test
  "Tests against the aoc.core namespace."
  (:require [clojure.test :refer [deftest is testing]]
            [aoc.core :as z]))

(deftest input->numbers-test
  (let [input (z/input->numbers "resources/day-1-input.txt")]
    (testing "converting file to sequence of numbers"
      (is 200 (count input)))

    (testing "first several values are what we expect"
      (is (= [1695 1157 1484 1717 622] (take 5 input))) )

    (testing "that every value is a number"
      (is (every? number? input)))))
