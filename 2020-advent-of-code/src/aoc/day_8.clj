(ns aoc.day-8
  "--- Day 8: Handheld Halting ---
  ...
  Run your copy of the boot code. Immediately before any instruction is
  executed a second time, what value is in the accumulator?"
  (:require [clojure.string :as string]))

(defn- parse-arg
  [arg]
  (let [[_ pm n] (re-find #"^([+-])(\d+)$" arg)
        i (Integer/parseInt n)]
    (case pm
      "+" i
      "-" (- i))))

(defn- parse-inst
  [line]
  (let [[inst arg] (string/split line #" ")]
    (case inst
      "nop" [0 1]
      "acc" [(parse-arg arg) 1]
      "jmp" [0 (parse-arg arg)])))

(defn value-before-loop
  [prog]
  (let [indexed (into {} (map-indexed vector prog))]
    (loop [acc 0
           pos 0
           visited #{}]
      (if (visited pos)
        acc
        (let [[a p] (parse-inst (get indexed pos))]
          (recur
            (+ acc a)
            (+ pos p)
            (conj visited pos)))))))
