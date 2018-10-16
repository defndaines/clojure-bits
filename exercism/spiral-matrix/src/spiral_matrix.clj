(ns spiral-matrix)

(defn open-fn
  "Create a function to check if the value at a given position in a grid is in
  bounds n and yet to be set."
  [n]
  (fn [grid [y x]]
    (and (< -1 x n)
         (< -1 y n)
         (zero? (get-in grid [y x])))))

(def move-fns
  "Infinite sequence of movement functions that will return a coordinate moved
  one position in the grid, adopting a clockwise turn with each function."
  (cycle
    [(fn [[y x]] [y (inc x)])
     (fn [[y x]] [(inc y) x])
     (fn [[y x]] [y (dec x)])
     (fn [[y x]] [(dec y) x])]))

(defn spiral [n]
  (let [sq (* n n)
        open? (open-fn n)]
    (loop [grid (vec (repeat n (vec (repeat n 0))))
           pos [0 0]
           v 1
           [move & r :as ms] move-fns]
      (if (< sq v)
        grid
        (if (open? grid pos)
          (recur (assoc-in grid pos v) pos (inc v) ms)
          (if (open? grid (move pos))
            (recur grid (move pos) v ms)
            (recur grid pos v r)))))))
