(ns aoc.day-17-test
  "Tests against the aoc.day-17 namespace."
  (:require [clojure.test :refer [deftest is testing]]
            [clojure.string :as string]
            [aoc.day-17 :as day-17]))

(def sample-input [".#."
                   "..#"
                   "###"])

(def input (string/split-lines (slurp "resources/day-17-input.txt")))

(deftest ->cube-test
  (testing "reading input into a cube structure."
    (is (= {[0 0 0] \. [0 1 0] \# [0 2 0] \.
            [1 0 0] \. [1 1 0] \. [1 2 0] \#
            [2 0 0] \# [2 1 0] \# [2 2 0] \#}
           (day-17/->cube sample-input)))))
