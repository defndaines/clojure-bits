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

(defn ->hypercube
  [input]
  (reduce merge
          (flatten
            (map-indexed
              (fn [ix y] (map-indexed (fn [iy ch] {[ix iy 0 0] ch}) y))
              input))))

(defn active [cube]
  (count (filter #(= \# %) (vals cube))))

(def neighbors-in-3d
  (filter #(not= [0 0 0] %)
          (for [x [-1 0 1] y [-1 0 1] z [-1 0 1]]
            [x y z])))

(def neighbors-in-4d
  (filter #(not= [0 0 0 0] %)
          (for [x [-1 0 1] y [-1 0 1] z [-1 0 1] w [-1 0 1]]
            [x y z w])))

(defn- get-neighbors
  [neighbors pos]
  (map #(mapv + pos %) neighbors))

(defn- update-state
  [cube neighbors pos]
  (let [current-state (get cube pos)
        active-neighbors (count
                           (filter #(= \# (get cube %)) (get-neighbors neighbors pos)))]
    (cond
      (and (not= \# current-state) (= 3 active-neighbors)) \#
      (and (= \# current-state) (< 1 active-neighbors 4)) \#
      :else \.)))

(defn round [cube neighbors]
  (let [all-neighbors (distinct (mapcat (partial get-neighbors neighbors) (keys cube)))]
    (into {} (map (fn [pos] [pos (update-state cube neighbors pos)]) all-neighbors))))

(defn game [cube neighbors]
  (lazy-seq (cons cube (game (round cube neighbors) neighbors))))
