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

(deftest active-test
  (testing "getting the active count from a cube."
    (is (= 5 (day-17/active (day-17/->cube sample-input))))))

(deftest game-test
  (testing "progressing the cube a round."
    (is (= 11 (day-17/active (day-17/round (day-17/->cube sample-input)))))
    (is (= 11 (day-17/active (nth (day-17/game (day-17/->cube sample-input)) 1))))
    (is (= 21 (day-17/active (nth (day-17/game (day-17/->cube sample-input)) 2))))
    (is (= 38 (day-17/active (nth (day-17/game (day-17/->cube sample-input)) 3))))
    (is (= 112 (day-17/active (nth (day-17/game (day-17/->cube sample-input)) 6))))
    (is (= 359 (day-17/active (nth (day-17/game (day-17/->cube input)) 6))))))
