(ns aoc.day-8
  "--- Day 8: Handheld Halting ---
  ...
  Run your copy of the boot code. Immediately before any instruction is
  executed a second time, what value is in the accumulator?
  --- Part Two ---
  ...
  Fix the program so that it terminates normally by changing exactly one jmp
  (to nop) or nop (to jmp). What is the value of the accumulator after the
  program terminates?"
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

(defn run-prog
  [prog]
  (let [indexed (into {} (map-indexed vector prog))]
    (loop [acc 0
           pos 0
           visited #{}]
      (if (visited pos)
        [:loops acc]
        (if-let [inst (get indexed pos)]
          (let [[a p] (parse-inst inst)]
            (recur
              (+ acc a)
              (+ pos p)
              (conj visited pos)))
          [:terminates acc])))))

(defn update-inst
  [prog line]
  (update prog line
          (fn [l]
            (case (first l)
              \n (string/replace l #"nop" "jmp")
              \j (string/replace l #"jmp" "nop")
              \a l))))

(defn find-terminating-prog
  [prog]
  (second
    (first
      (filter
        #(= :terminates (first %))
        (for [n (range (count prog))]
          (run-prog (update-inst prog n)))))))
