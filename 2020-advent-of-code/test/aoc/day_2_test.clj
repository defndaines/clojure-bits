(ns aoc.day-2-test
  "Tests against the aoc.day-2 namespace."
  (:require [clojure.test :refer [deftest is testing]]
            [clojure.string :as string]
            [aoc.day-2 :as day-2]))

(def sample-data
  ["1-3 a: abcde"
   "1-3 b: cdefg"
   "2-9 c: ccccccccc"])

(def input (string/split-lines (slurp "resources/day-2-input.txt")))

(deftest regex-test
  (testing "able to parse line into parts"
    (is (= [9 12 \q "qqqxhnhdmqqqqjz"]
           (day-2/parse-line "9-12 q: qqqxhnhdmqqqqjz")))))

(deftest valid?-test
  (testing "valid passwords that follow the policies set forth"
    (is (day-2/valid? "1-3 a: abcde"))
    (is (day-2/valid? "2-9 c: ccccccccc")))

  (testing "invalid password that does not follow the policy"
    (is (not (day-2/valid? "1-3 b: cdefg")))))

(deftest number-of-valid-passwords-test
  (testing "sample data has two"
    (is (= 2 (count (filter day-2/valid? sample-data)))))
  (testing "puzzle input"
    (is (= 569 (count (filter day-2/valid? input))))))

(deftest toboggan-valid?-test
  (testing "scheme set forth in part 2 is valid"
    (is (day-2/toboggan-valid? "1-3 a: abcde")))

  (testing "scheme set forth in part 2 is not valid"
    (is (not (day-2/toboggan-valid? "1-3 b: cdefg")))
    (is (not (day-2/toboggan-valid? "2-9 c: ccccccccc")))))

(deftest number-of-toboggan-valid-passwords-test
  (testing "sample data has one"
    (is (= 1 (count (filter day-2/toboggan-valid? sample-data)))))
  (testing "puzzle input"
    (is (= 346 (count (filter day-2/toboggan-valid? input))))))
