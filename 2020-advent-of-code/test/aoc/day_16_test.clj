(ns aoc.day-16-test
  "Tests against the aoc.day-16 namespace."
  (:require [clojure.test :refer [deftest is testing]]
            [clojure.string :as string]
            [aoc.day-16 :as day-16]))

(def sample-notes
  ["class: 1-3 or 5-7"
   "row: 6-11 or 33-44"
   "seat: 13-40 or 45-50"
   ""
   "your ticket:"
   "7,1,14"
   ""
   "nearby tickets:"
   "7,3,47"
   "40,4,50"
   "55,2,20"
   "38,6,12"])

(def input (string/split-lines (slurp "resources/day-16-input.txt")))

(deftest validate-test
  (testing "validation of possible fields."
    (is (nil? (day-16/validate sample-notes [7 3 47])))
    (is (= 4 (day-16/validate sample-notes [40 4 50])))
    (is (= 55 (day-16/validate sample-notes [55 2 20])))
    (is (= 12 (day-16/validate sample-notes [38 6 12])))))

(deftest scanning-error-rate-test
  (testing "scanning error rate is sum of invalid fields."
    (is (= 71 (day-16/scanning-error-rate sample-notes)))
    (is (= 25961 (day-16/scanning-error-rate input)))))
