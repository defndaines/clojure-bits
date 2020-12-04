(ns aoc.day-4-test
  "Tests against the aoc.day-4 namespace."
  (:require [clojure.test :refer [deftest is testing]]
            [clojure.string :as string]
            [aoc.day-4 :as day-4]))

(def sample-data
  "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
byr:1937 iyr:2017 cid:147 hgt:183cm

iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
hcl:#cfa07d byr:1929

hcl:#ae17e1 iyr:2013
eyr:2024
ecl:brn pid:760753108 byr:1931
hgt:179cm

hcl:#cfa07d eyr:2025 pid:166559648
iyr:2011 ecl:brn hgt:59in")

(def input (slurp "resources/day-4-input.txt"))

(deftest passport-valid?-test
  (testing "valid passport entry, with all entries"
    (is (day-4/passport-valid?
          "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
          byr:1937 iyr:2017 cid:147 hgt:183cm"))
    (is (day-4/passport-valid?
          "hcl:#ae17e1 iyr:2013
          eyr:2024
          ecl:brn pid:760753108 byr:1931
          hgt:179cm")))

  (testing "invalid passort entires, with missing entries"
    (is (not (day-4/passport-valid?
               "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
               hcl:#cfa07d byr:1929")))
    (is (not (day-4/passport-valid?
               "hcl:#cfa07d eyr:2025 pid:166559648
               iyr:2011 ecl:brn hgt:59in"))))

  (testing "valid entries in puzzle input"
    (let [entries (string/split input #"\n\n")]
      (is (= 216
           (count (filter day-4/passport-valid? entries)))))))
