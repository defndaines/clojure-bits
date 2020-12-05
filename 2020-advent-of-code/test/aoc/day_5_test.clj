(ns aoc.day-5-test
  "Tests against the aoc.day-5 namespace."
  (:require [clojure.test :refer [deftest is testing]]
            [clojure.string :as string]
            [aoc.day-5 :as day-5]))

(def input (string/split-lines (slurp "resources/day-5-input.txt")))

(deftest seat-id-test
  (testing "derive seat ID from boarding passes."
    (is (= 567 (day-5/seat-id "BFFFBBFRRR")))
    (is (= 119 (day-5/seat-id "FFFBBBFRRR")))
    (is (= 820 (day-5/seat-id "BBFFBBFRLL")))))

(deftest highest-seat-id-test
  (testing "find highest seat ID in input."
    (is (= 855 (reduce max (map day-5/seat-id input))))))

(deftest your-seat-test
  (testing "find your seat, where the seats next to it will exist."
    (is (= 552
           (->> input
                (map day-5/seat-id)
                sort
                (reduce (fn [acc e] (if (= (+ 1 acc) e) e acc)))
                inc)))))
