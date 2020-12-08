(ns aoc.day-8-test
  "Tests against the aoc.day-8 namespace."
  (:require [clojure.test :refer [deftest is testing]]
            [clojure.string :as string]
            [aoc.day-8 :as day-8]))

(def sample-data
  ["nop +0"
   "acc +1"
   "jmp +4"
   "acc +3"
   "jmp -3"
   "acc -99"
   "acc +1"
   "jmp -4"
   "acc +6"])

(def input (string/split-lines (slurp "resources/day-8-input.txt")))

(deftest value-before-loop-test
  (testing "accumulator value before code enters infinite loop."
    (is (= 5 (second (day-8/run-prog sample-data))))
    (is (= 2025 (second (day-8/run-prog input))))))

(deftest find-terminating-prog-test
  (testing "finding the accumulator on a version of the program which terminates."
    (is (= 8 (day-8/find-terminating-prog sample-data)))
    (is (= 2001 (day-8/find-terminating-prog input)))))
