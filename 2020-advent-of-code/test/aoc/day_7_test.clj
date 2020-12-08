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

(deftest bag-contains-test
  (testing "number of bags which a given color must hold."
    (is (= 32 (day-7/bag-contains sample-rules "shiny gold")))
    (is (= 126
           (day-7/bag-contains ["shiny gold bags contain 2 dark red bags."
                                "dark red bags contain 2 dark orange bags."
                                "dark orange bags contain 2 dark yellow bags."
                                "dark yellow bags contain 2 dark green bags."
                                "dark green bags contain 2 dark blue bags."
                                "dark blue bags contain 2 dark violet bags."
                                "dark violet bags contain no other bags."]
                               "shiny gold")))
    (is (= 3765
           (day-7/bag-contains input "shiny gold")))))
