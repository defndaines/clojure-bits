(ns aoc.day-1-test
  "Tests against the aoc.day-1 namespace."
  (:require [clojure.test :refer [deftest is testing]]
            [aoc.core :as z]
            [aoc.day-1 :as day-1]))

(def sample-data [1721 979 366 299 675 1456])

(def input (z/input->numbers "resources/day-1-input.txt"))

(deftest sum-to-2020-test
  (testing "chooses two values that sum to 2020"
    (is (= [1721 299]
           (day-1/sum-to-2020 sample-data)))
    (is (= [138 1882]
           (day-1/sum-to-2020 input)))))

(deftest triple-sum-test
  (testing "choose three values that sum to 2020"
    (is (= [979 366 675]
           (day-1/triple-sum sample-data)))
    (is (= [272 308 1440]
           (day-1/triple-sum input)))))

(deftest product-2020-test
  (testing "product of 1721 and 299 from list"
    (is (= 514579
           (apply * (day-1/sum-to-2020 sample-data)))))
  (testing "part one which is the product of two numbers"
    (is (= 259716
           (apply * (day-1/sum-to-2020 input)))) )
  (testing "part two which is the product of three numbers"
    (is (= 120637440
           (apply * (day-1/triple-sum input))))))
