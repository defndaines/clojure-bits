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

(def part-two-sample
  ["class: 0-1 or 4-19"
   "row: 0-5 or 8-19"
   "seat: 0-13 or 16-19"
   ""
   "your ticket:"
   "11,12,13"
   ""
   "nearby tickets:"
   "3,9,18"
   "15,1,5"
   "5,14,9"])

(deftest identify-fields-test
  (testing "able to identify fields on input."
    (is (= {"row" 0
            "class" 1
            "seat" 2}
           (day-16/identify-fields part-two-sample)))
    (is (= {"arrival station" 10
            "arrival location" 15
            "arrival platform" 9
            "arrival track" 7
            "class" 6
            "departure date" 17
            "departure location" 16
            "departure platform" 1
            "departure station" 2
            "departure time" 13
            "departure track" 5
            "duration" 18
            "price" 14
            "route" 3
            "row" 4
            "seat" 8
            "train" 11
            "type" 0
            "wagon" 12
            "zone" 19}
           (day-16/identify-fields input)))))

(deftest departure-values-test
  (testing "sum of values associated with departure fields."
    (let [ticket (day-16/my-ticket input)
          fields (day-16/identify-fields input)
          departures (vals (filter #(string/starts-with? (first %) "departure") fields))]
      (is (= 603409823791 (reduce * (vals (select-keys ticket departures))))))))
