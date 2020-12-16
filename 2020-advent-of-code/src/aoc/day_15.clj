(ns aoc.day-15
  "--- Day 15: Rambunctious Recitation ---
  ...
  Given your starting numbers, what will be the 2020th number spoken?")

(defn game
  [seed round]
  (loop [acc {}
         input seed
         cnt 1
         last-number nil]
    (if (< round cnt)
      last-number
      (if (seq input)
        (recur (update acc (first input) conj cnt) (rest input) (inc cnt) (first input))
        (let [[a b] (get acc last-number)]
          (if (nil? b)
            (recur (update acc 0 conj cnt) input (inc cnt) 0)
            (let [d (- a b)]
              (recur (update acc d conj cnt) input (inc cnt) d))))))))

