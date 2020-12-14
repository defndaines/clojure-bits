(ns aoc.day-14
  "--- Day 14: Docking Data ---
  ...
  Execute the initialization program. What is the sum of all values left in
  memory after it completes?")

(defn apply-mask
  [n mask]
  (let [bin (Long/toBinaryString n)
        nb (apply str (concat (repeat (- (count mask) (count bin)) \0) bin))]
    (Long/parseUnsignedLong
      (apply str (map (fn [b m] (if (= \X m) b m)) nb mask))
      2)))
