(ns aoc.day-19
  "--- Day 19: Monster Messages ---
  ...
  How many messages completely match rule 0?"
  (:require [clojure.string :as string])
  (:import [java.util.regex Pattern]))

(defn- parse-rule
  [line]
  (let [[_ id rule] (re-find #"^(\d+): (.*)$" line)
        alt? (< -1 (.indexOf rule "|"))
        str? (< -1 (.indexOf rule "\""))]
    (cond
      str? {id [:str (string/replace rule #"\"" "")]}
      alt? {id [:alt (map #(string/split % #" ") (string/split rule #" *\| *"))]}
      :else {id [:seq (string/split rule #" ")]})))

(defn build-rules
  [rules]
  (reduce merge {} (map parse-rule rules)))

(defn- build-regex
  [rules id]
  (let [[k v] (get rules id)]
    (case k
      :str v
      :seq (apply str (map (partial build-regex rules) v))
      :alt (str "("
                (string/join
                  "|"
                  (map #(apply str (map (partial build-regex rules) %)) v))
                ")"))))

(defn regex-of
  [rules id]
  (Pattern/compile (str "^" (build-regex (build-rules rules) id) "$")))
