(ns aoc.day-6-test
  "Tests against the aoc.day-6 namespace."
  (:require [clojure.test :refer [deftest is testing]]
            [clojure.string :as string]
            [aoc.day-6 :as day-6]))

(def input (slurp "resources/day-6-input.txt"))

(def groups (string/split input #"\n\n"))

(deftest group-yes-test
  (testing "yes counts for given groups."
    (is (= 3 (day-6/group-yes "abc")))
    (is (= 3 (day-6/group-yes "a
                              b
                              c")))
    (is (= 3 (day-6/group-yes "ab
                              ac")))
    (is (= 1 (day-6/group-yes "a
                              a
                              a
                              a")))
    (is (= 1 (day-6/group-yes "b"))))

  (testing "sum of input group yes counts."
    (is (= 6542
           (reduce
             +
             (map day-6/group-yes groups))))))

(deftest group-all-yes-test
  (testing "counts where all members of group answered yes."
    (is (= 3 (day-6/group-all-yes "abc")))
    (is (= 0 (day-6/group-all-yes "a
                                  b
                                  c")))
    (is (= 1 (day-6/group-all-yes "ab
                                  ac")))
    (is (= 1 (day-6/group-all-yes "a
                                  a
                                  a
                                  a")))
    (is (= 1 (day-6/group-all-yes "b"))))

  (testing "sum of all-yes counts across groups."
    (is (= 3299
           (reduce
             +
             (map day-6/group-all-yes groups))))))
