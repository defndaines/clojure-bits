(ns aoc.day-12
  "--- Day 12: Rain Risk ---
  ...
  What is the Manhattan distance between that location and the ship's starting
  position?")

(defn manhattan-distance
  [coordinates]
  (let [[x y] coordinates]
    (+ (Math/abs x) (Math/abs y))))

(defn- forward
  [dir x y dist]
  (case dir
    "N" [dir [x (+ y dist)]]
    "S" [dir [x (- y dist)]]
    "E" [dir [(+ x dist) y]]
    "W" [dir [(- x dist) y]]))

(def cw (cycle ["N" "E" "S" "W"]))
(def ccw (cycle ["N" "W" "S" "E"]))

(defn- rotate
  [orient degrees start]
  (let [x (/ degrees 90)]
    (first (drop x (drop-while #(not= start %) orient)))))

(defn move
  [start inst]
  (let [[dir [x y]] start
        [_ cmd i] (re-find #"^([EFLNRSW])(\d+)$" inst)
        v (Integer/parseInt i)]
    (case cmd
      "N" [dir [x (+ y v)]]
      "S" [dir [x (- y v)]]
      "E" [dir [(+ x v) y]]
      "W" [dir [(- x v) y]]
      "L" [(rotate ccw v dir) [x y]]
      "R" [(rotate cw v dir) [x y]]
      "F" (forward dir x y v))))
