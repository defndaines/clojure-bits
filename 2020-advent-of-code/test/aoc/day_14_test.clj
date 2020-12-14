(ns aoc.day-14-test
  "Tests against the aoc.day-14 namespace."
  (:require [clojure.test :refer [deftest is testing]]
            [aoc.day-14 :as day-14]))

(def sample-mask "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X")

(deftest apply-mask-test
  (testing "applying a bitmask to number"
    (is (= 73 (day-14/apply-mask 11 sample-mask)))
    (is (= 101 (day-14/apply-mask 101 sample-mask)))
    (is (= 64 (day-14/apply-mask 0 sample-mask)))))
