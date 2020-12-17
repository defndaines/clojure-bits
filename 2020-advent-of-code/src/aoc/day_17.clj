(ns aoc.day-17
  "--- Day 17: Conway Cubes ---
  ...
  Starting with your given initial configuration, simulate six cycles. How
  many cubes are left in the active state after the sixth cycle?")

(defn ->cube
  [input]
  (reduce merge
          (flatten
            (map-indexed
              (fn [ix y] (map-indexed (fn [iy ch] {[ix iy 0] ch}) y))
              input))))
