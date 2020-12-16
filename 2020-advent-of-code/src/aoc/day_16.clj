(ns aoc.day-16
  "--- Day 16: Ticket Translation ---
  ...
  Consider the validity of the nearby tickets you scanned. What is your ticket
  scanning error rate?
  --- Part Two ---
  ...
  Once you work out which field is which, look for the six fields on your
  ticket that start with the word departure. What do you get if you multiply
  those six values together?"
  (:require [clojure.string :as string]
            [clojure.set :as set]))

(defn- parse-rules
  [input]
  (let [rules-section (take-while #(not= "" %) input)]
    (reduce
      (fn [acc e]
        (let [[_ a b c d] (re-find #"^.*: (\d+)-(\d+) or (\d+)-(\d+)$" e)]
          (into
            (into acc (range (Integer/parseInt a) (inc (Integer/parseInt b))))
            (range (Integer/parseInt c) (inc (Integer/parseInt d))))))
      #{}
      rules-section)))

(defn validate
  [input ticket]
  (let [rules (parse-rules input)]
    (first (remove rules ticket))))

(defn- line->ticket
  [line]
  (mapv #(Integer/parseInt %) (string/split line #",")))

(defn scanning-error-rate
  [input]
  (let [rules (parse-rules input)
        nearby (drop-while #(not= "nearby tickets:" %) input)]
    (->> (rest nearby)
         (map line->ticket)
         (map (partial remove rules))
         (keep first)
         (apply +))))

(defn- parse-rules-with-names
  [input]
  (let [rules-section (take-while #(not= "" %) input)]
    (reduce
      (fn [acc e]
        (let [[_ rule a b c d] (re-find #"^(.*): (\d+)-(\d+) or (\d+)-(\d+)$" e)]
          (assoc acc
                 rule
                 (into
                   (set (range (Integer/parseInt a) (inc (Integer/parseInt b))))
                   (range (Integer/parseInt c) (inc (Integer/parseInt d)))))))
      {}
      rules-section)))

(defn- nearby-tickets
  [input]
  (let [nearby (drop-while #(not= "nearby tickets:" %) input)]
    (map line->ticket (rest nearby))))

(defn- indexed
  [tickets]
  (reduce
    (fn [acc e]
      (reduce
        (fn [acc' [i v]]
          (update acc' i conj v))
        acc
        (map-indexed vector e)))
    {}
    tickets))

(defn- possibilities
  [rules indexed-tickets]
  (reduce
      (fn [acc [r s]]
        (assoc acc
               r
               (keep (fn [[i vs]] (when (every? s vs) i)) indexed-tickets)))
      {}
      rules))

(defn- process-of-elimination
  [possible]
  (loop [acc {}
         to-check possible]
    (if (seq to-check)
      (let [solos (keep (fn [[k v]]
                          (when (= 1 (count v))
                            [k (first v)]))
                        to-check)
            taken (set (map second solos))]
        (recur
          (into acc solos)
          (reduce-kv
            (fn [acc k v]
              (let [left (remove taken v)]
                (if (seq left) (assoc acc k left) acc)))
            {}
            to-check)))
      acc)))

(defn identify-fields
  [input]
  (let [rules (parse-rules-with-names input)
        nearby (nearby-tickets input)
        valid? (reduce set/union #{} (vals rules))
        valid-only (filter #(empty? (remove valid? %)) nearby)
        valid-indexed (indexed valid-only)
        possibles (possibilities rules valid-indexed)]
    (process-of-elimination possibles)))

(defn my-ticket
  [input]
  (let [data (drop-while #(not= "your ticket:" %) input)]
    (line->ticket (second data))))
