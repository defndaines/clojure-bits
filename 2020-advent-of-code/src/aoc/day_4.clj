(ns aoc.day-4
  "--- Day 4: Passport Processing ---
  ...
  Count the number of valid passports - those that have all required fields.
  Treat cid as optional. In your batch file, how many passports are valid?"
  (:require [clojure.string :as string]
            [clojure.set :as set]))

(def required-fields #{"byr" "iyr" "eyr" "hgt" "hcl" "ecl" "pid"})

(defn passport-valid?
  [entry]
  (let [kvs (string/split entry #"\s+")
        ks (into #{} (map #(-> % (string/split #":") first)) kvs)]
    (set/subset? required-fields ks)))
