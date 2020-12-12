(ns aoc.day-12-test
  "Tests against the aoc.day-12 namespace."
  (:require [clojure.test :refer [deftest is testing]]
            [clojure.string :as string]
            [aoc.day-12 :as day-12]))

(def starting-position ["E" [0 0]])

(def sample-data ["F10" "N3" "F7" "R90" "F11"])

(def input (string/split-lines (slurp "resources/day-12-input.txt")))

(deftest move-test
  (testing "moving based upon input."
    (is (= ["E" [10 0]] (day-12/move starting-position "F10")))
    (is (= ["E" [10 3]] (day-12/move ["E" [10 0]] "N3")))
    (is (= ["E" [17 3]] (day-12/move ["E" [10 3]] "F7")))
    (is (= ["S" [17 3]] (day-12/move ["E" [17 3]] "R90")))
    (is (= ["S" [17 -8]] (day-12/move ["S" [17 3]] "F11")))
    (is (= ["S" [17 -8]] (reduce day-12/move starting-position sample-data)))
    (is (= ["W" [71 -688]] (reduce day-12/move starting-position input)))))

(deftest manhattan-distance-test
  (testing "manhattan distance from starting position."
    (is (= 25 (day-12/manhattan-distance [17 -8])))
    (is (= 759 (day-12/manhattan-distance [71 -688])))
    (is (= 286 (day-12/manhattan-distance [214 -72])))
    (is (= 45763 (day-12/manhattan-distance [24120 21643])))))

(def starting-waypoint [10 1])

(deftest waypoint-move-test
  (testing "moving using a waypoint."
    (is (= [[100 10] starting-waypoint]
           (day-12/waypoint-move [[0 0] starting-waypoint] "F10")))
    (is (= [[100 10] [10 4]]
           (day-12/waypoint-move [[100 10] starting-waypoint] "N3")))
    (is (= [[170 38] [10 4]]
           (day-12/waypoint-move [[100 10] [10 4]] "F7")))
    (is (= [[170 38] [4 -10]]
           (day-12/waypoint-move [[170 38] [10 4]] "R90")))
    (is (= [[214 -72] [4 -10]]
           (day-12/waypoint-move [[170 38] [4 -10]] "F11")))
    (is (= [[214 -72] [4 -10]]
           (reduce day-12/waypoint-move [[0 0] starting-waypoint] sample-data)))
    (is (= [[24120 21643] [44 -2]]
           (reduce day-12/waypoint-move [[0 0] starting-waypoint] input)))))
