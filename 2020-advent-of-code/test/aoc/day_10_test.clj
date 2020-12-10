(ns aoc.day-10-test
  "Tests against the aoc.day-10 namespace."
  (:require [clojure.test :refer [deftest is testing]]
            [aoc.core :as aoc]
            [aoc.day-10 :as day-10]))

(def sample-data [16 10 15 5 1 11 7 19 6 12 4])

(def larger-sample [28 33 18 42 31 14 46 20 48 47 24 23 49 45 19 38 39 11 1 32
                    25 35 8 17 7 9 4 2 34 10 3])

(def input (aoc/input->numbers "resources/day-10-input.txt"))

(deftest diff-counts-test
  (testing "jolt difference counts across adapters."
    (is (= 7 (get (day-10/diff-counts sample-data) 1)))
    (is (= 5 (get (day-10/diff-counts sample-data) 3)))
    (is (= 22 (get (day-10/diff-counts larger-sample) 1)))
    (is (= 10 (get (day-10/diff-counts larger-sample) 3)))
    (is (= 75 (get (day-10/diff-counts input) 1)))
    (is (= 33 (get (day-10/diff-counts input) 3)))))

(deftest distinct-arrangments-test
  (testing "number of distinct arrangments of a set of adapters."
    (is (= 8 (day-10/distinct-arrangments sample-data)))
    (is (= 19208 (day-10/distinct-arrangments larger-sample)))
    ; (is (= 19208 (day-10/distinct-arrangments input)))
    ))
