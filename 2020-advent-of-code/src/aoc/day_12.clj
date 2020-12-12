(ns aoc.day-12
  "--- Day 12: Rain Risk ---
  ...
  What is the Manhattan distance between that location and the ship's starting
  position?
  --- Part Two ---
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

(defn rotate-waypoint
  [orient degrees x y]
  (let [angle (if (= :ccw orient) (- 360 degrees) degrees)]
    (case angle
      90 [y (- x)]
      180 [(- x) (- y)]
      270 [(- y) x]
      [x y])))

(defn waypoint-move
  [start inst]
  (let [[[x y] [wx wy]] start
        [_ cmd i] (re-find #"^([EFLNRSW])(\d+)$" inst)
        v (Integer/parseInt i)]
    (case cmd
      "N" [[x y] [wx (+ wy v)]]
      "S" [[x y] [wx (- wy v)]]
      "E" [[x y] [(+ wx v) wy]]
      "W" [[x y] [(- wx v) wy]]
      "L" [[x y] (rotate-waypoint :ccw v wx wy)]
      "R" [[x y] (rotate-waypoint :cw v wx wy)]
      "F" [[(+ x (* v wx)) (+ y (* v wy))] [wx wy]])))
