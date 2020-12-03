(ns aoc.day-1
  "Day 1: Report Repair
  ...
  Specifically, they need you to find the two entries that sum to 2020 and
  then multiply those two numbers together.

  --- Part Two ---
  ...
  In your expense report, what is the product of the three entries that sum to
  2020?")

(defn- check-head
  [l]
  (when-let [x (first l)]
    (loop [r (rest l)]
      (when-let [y (first r)]
        (if (= 2020 (+ x y))
          [x y]
          (recur (rest r)))))))

(defn sum-to-2020
  "Find a pair of values that sum to 2020."
  [l]
  (loop [l l]
    (if-let [pair (check-head l)]
      pair
      (recur (rest l)))))

(defn- check-three
  [l]
  (when-let [x (first l)]
    (loop [r (rest l)]
      (when-let [y (first r)]
        (if (and (> 2020 (+ x y))
                 (let [z (- 2020 x y)]
                   (some #(= z %) r)))
          [x y (- 2020 x y)]
          (recur (rest r)))))))

(defn triple-sum
  "Find three values that sum to 2020."
  [l]
  (loop [l l]
    (if-let [triple (check-three l)]
      triple
      (recur (rest l)))))
