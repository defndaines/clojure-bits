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

(deftest fields-present?-test
  (testing "valid passport entry, with all entries"
    (is (day-4/fields-present?
          "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
          byr:1937 iyr:2017 cid:147 hgt:183cm"))
    (is (day-4/fields-present?
          "hcl:#ae17e1 iyr:2013
          eyr:2024
          ecl:brn pid:760753108 byr:1931
          hgt:179cm")))

  (testing "invalid passort entires, with missing entries"
    (is (not (day-4/fields-present?
               "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
               hcl:#cfa07d byr:1929")))
    (is (not (day-4/fields-present?
               "hcl:#cfa07d eyr:2025 pid:166559648
               iyr:2011 ecl:brn hgt:59in"))))

  (testing "valid entries in puzzle input"
    (let [entries (string/split input #"\n\n")]
      (is (= 216
           (count (filter day-4/fields-present? entries)))))))

(deftest valid-byr?-test
  (testing "valid birth year"
    (is (day-4/valid-byr? "2002")))
  (testing "invalid birth year"
    (is (not (day-4/valid-byr? "2003")))))

(deftest valid-hgt?-test
  (testing "valid heights"
    (is (day-4/valid-hgt? "60in"))
    (is (day-4/valid-hgt? "190cm")))
  (testing "invalid heights"
    (is (not (day-4/valid-hgt? "190in")))
    (is (not (day-4/valid-hgt? "190")))))

(deftest valid-hcl?-test
  (testing "valid hair color"
    (is (day-4/valid-hcl? "#123abc")))
  (testing "invalid hair colors"
    (is (not (day-4/valid-hcl? "#123abz")))
    (is (not (day-4/valid-hcl? "123abc")))))

(deftest valid-ecl?-test
  (testing "valid eye color"
    (is (day-4/valid-ecl? "brn")))
  (testing "invalid eye color"
    (is (not (day-4/valid-ecl? "wat")))))

(deftest valid-pid?-test
  (testing "valid passport ID"
    (is (day-4/valid-pid? "000000001")))
  (testing "invalid passport ID"
    (is (not (day-4/valid-pid? "0123456789")))))

(deftest valid-passport?-test
  (testing "invalid passports"
    (is (not (day-4/valid-passport?
               "eyr:1972 cid:100
               hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926")))
    (is (not (day-4/valid-passport?
               "iyr:2019
               hcl:#602927 eyr:1967 hgt:170cm
               ecl:grn pid:012533040 byr:1946")))
    (is (not (day-4/valid-passport?
               "hcl:dab227 iyr:2012
               ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277")))
    (is (not (day-4/valid-passport?
               "hgt:59cm ecl:zzz
               eyr:2038 hcl:74454a iyr:2023
               pid:3556412378 byr:2007"))))

  (testing "valid passports"
    (is (day-4/valid-passport?
          "pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980
          hcl:#623a2f"))
    (is (day-4/valid-passport?
          "eyr:2029 ecl:blu cid:129 byr:1989
          iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm"))
    (is (day-4/valid-passport?
          "hcl:#888785
          hgt:164cm byr:2001 iyr:2015 cid:88
          pid:545766238 ecl:hzl
          eyr:2022"))
    (is (day-4/valid-passport?
          "iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719")))

  (testing "count of valid passports in input"
    (is (= 150
           (->> (string/split input #"\n\n")
                (filter day-4/valid-passport?)
                count)))))
