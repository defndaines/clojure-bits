(ns aoc.day-14-test
  "Tests against the aoc.day-14 namespace."
  (:require [clojure.test :refer [deftest is testing]]
            [clojure.string :as string]
            [aoc.day-14 :as day-14]))

(def sample-mask "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X")

(def sample-data
  ["mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X"
   "mem[8] = 11"
   "mem[7] = 101"
   "mem[8] = 0"])

(def input (string/split-lines (slurp "resources/day-14-input.txt")))

(deftest apply-mask-test
  (testing "applying a bitmask to number"
    (is (= 73 (day-14/apply-mask 11 sample-mask)))
    (is (= 101 (day-14/apply-mask 101 sample-mask)))
    (is (= 64 (day-14/apply-mask 0 sample-mask)))))

(deftest run-program-test
  (testing "run program to derive final numbers."
    (is (= {7 101
            8 64}
           (day-14/run-program sample-data)))
    (is (= 165 (reduce + (vals (day-14/run-program sample-data)))))
    (is (= 12135523360904 (reduce + (vals (day-14/run-program input)))))))
