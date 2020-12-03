(ns aoc.day-3
  "--- Day 3: Toboggan Trajectory ---
  ...
  Starting at the top-left corner of your map and following a slope of right 3
  and down 1, how many trees would you encounter?
  --- Part Two ---
  ...
  What do you get if you multiply together the number of trees encountered on
  each of the listed slopes?")

(defn trees-on-slope
  "Get the number of trees ('#') encountered traversing the provided slope."
  [hill right down]
  (let [width (count (first hill))]
    (first
      (reduce
        (fn [[acc pos] line]
          [(if (= \# (.charAt line pos))
             (+ acc 1)
             acc)
           (mod (+ pos right) width)])
        [0 0]
        (take-nth down hill)))))
