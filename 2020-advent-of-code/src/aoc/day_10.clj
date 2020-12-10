(ns aoc.day-10
  "--- Day 10: Adapter Array ---
  ...
  What is the number of 1-jolt differences multiplied by the number of 3-jolt
  differences?")

(defn diff-counts
  [s]
  (let [sorted (conj (sort s) 0) ; Add in the outlet, 0.
        built-in (+ 3 (last sorted)) ; built-in adapater always 3 more than highest.
        all (conj (vec sorted) built-in)]
    (->> all
         (partition 2 1)
         (map #(apply (comp - -) %))
         frequencies)))
