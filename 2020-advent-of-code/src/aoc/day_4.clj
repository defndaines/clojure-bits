(ns aoc.day-4
  "--- Day 4: Passport Processing ---
  ...
  Count the number of valid passports - those that have all required fields.
  Treat cid as optional. In your batch file, how many passports are valid?
  --- Part Two ---
  ...
  Count the number of valid passports - those that have all required fields
  and valid values. Continue to treat cid as optional. In your batch file, how
  many passports are valid?"
  (:require [clojure.string :as string]
            [clojure.set :as set]))

(def required-fields #{"byr" "iyr" "eyr" "hgt" "hcl" "ecl" "pid"})

(defn fields-present?
  [entry]
  (let [kvs (string/split entry #"\s+")
        ks (into #{} (map #(-> % (string/split #":") first)) kvs)]
    (set/subset? required-fields ks)))

(defn- valid-date?
  [year from to]
  (when-let [digits (re-find #"^[12][09]\d\d$" year)]
    (<= from (Integer/parseInt digits) to)))

(defn valid-byr?
  "A valid birth year has four digits; at least 1920 and at most 2002."
  [byr]
  (valid-date? byr 1920 2002))

(defn valid-iyr?
  "A valid issue year has four digits; at least 2010 and at most 2020."
  [iyr]
  (valid-date? iyr 2010 2020))

(defn valid-eyr?
  "A valid expiration year has four digits; at least 2020 and at most 2030."
  [eyr]
  (valid-date? eyr 2020 2030))

(defn valid-hgt?
  "A valid height is a number followed by either cm or in:
  - If cm, the number must be at least 150 and at most 193.
  - If in, the number must be at least 59 and at most 76."
  [hgt]
  (when-let [match (re-find #"^(\d{2,3})(cm|in)$" hgt)]
    (let [v (Integer/parseInt (second match))
          units (last match)]
      (case units
        "cm" (<= 150 v 193)
        "in" (<= 59 v 76)))))

(defn valid-hcl?
  "A valid hair color is a # followed by exactly six characters 0-9 or a-f."
  [hcl]
  (re-find #"^#\p{XDigit}{6}$" hcl))

(defn valid-ecl?
  "A valid eye color is exactly one of: amb blu brn gry grn hzl oth."
  [ecl]
  (contains? #{"amb" "blu" "brn" "gry" "grn" "hzl" "oth"} ecl))

(defn valid-pid?
  "A valid passport ID is a nine-digit number, including leading zeroes."
  [pid]
  (re-find #"^\d{9}$" pid))

(defn valid-passport?
  [entry]
  (let [kvs (string/split entry #"\s+")
        passport (into {} (map #(string/split % #":")) kvs)]
    (and
     (set/subset? required-fields (set (keys passport)))
     (valid-byr? (get passport "byr"))
     (valid-iyr? (get passport "iyr"))
     (valid-eyr? (get passport "eyr"))
     (valid-hgt? (get passport "hgt"))
     (valid-hcl? (get passport "hcl"))
     (valid-ecl? (get passport "ecl"))
     (valid-pid? (get passport "pid")))))
