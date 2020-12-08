(ns aoc.day-7-test
  "Tests against the aoc.day-7 namespace."
  (:require [clojure.test :refer [deftest is testing]]
            [clojure.string :as string]
            [aoc.day-7 :as day-7]))

(def sample-rules
  ["light red bags contain 1 bright white bag, 2 muted yellow bags."
   "dark orange bags contain 3 bright white bags, 4 muted yellow bags."
   "bright white bags contain 1 shiny gold bag."
   "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags."
   "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags."
   "dark olive bags contain 3 faded blue bags, 4 dotted black bags."
   "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags."
   "faded blue bags contain no other bags."
   "dotted black bags contain no other bags."])

(def input (string/split-lines (slurp "resources/day-7-input.txt")))

(deftest bag-options-test
  (testing "number of different colors which can hold a given color bag."
    (is (= 4 (count (day-7/bag-options sample-rules "shiny gold")))))
  (testing "number of bags based upon input rules"
    (is (= 261 (count (day-7/bag-options input "shiny gold"))))))
