(ns aoc.day-9-test
  "Tests against the aoc.day-9 namespace."
  (:require [clojure.test :refer [deftest is testing]]
            [aoc.core :as aoc]
            [aoc.day-9 :as day-9]))

(def sample-data
  [35 20 15 25 47 40 62 55 65 95 102 117 150 182 127 219 299 277 309 576])

(def input (aoc/input->numbers "resources/day-9-input.txt"))

(deftest valid?-test
  (let [first-sample (range 1 26)
        second-sample (concat (range 1 20) (range 21 26) [45])]
    (testing "next number is valid."
      (is (day-9/valid? first-sample 26))
      (is (day-9/valid? first-sample 49))
      (is (day-9/valid? second-sample 26))
      (is (day-9/valid? second-sample 66))
      (is (day-9/valid? second-sample 64)))
    (testing "next number is not valid."
      (is (not (day-9/valid? first-sample 100)))
      (is (not (day-9/valid? first-sample 50)))
      (is (not (day-9/valid? second-sample 65))))))

(deftest find-invalid-test
  (testing "find the first invalid number in sequence."
    (is (= 127 (day-9/find-invalid sample-data 5)))
    (is (= 1721308972 (day-9/find-invalid input 25)))))
