(ns minesweeper)

(def ^:private offsets
  "Sequence of offsets to get immediate neighbors in a minefield."
  (for [x [-1 0 1] y [-1 0 1] :when (not (every? zero? [x y]))] [x y]))

(defn ^:private add-mine
  "Add a mine count to the field at the given coordinates."
  [field coord]
  (if coord
    (let [at (get-in field coord)]
      (case (get-in field coord)
        \* field
        \space (assoc-in field coord 1)
        (update-in field coord inc)))
    field))

(defn draw [s]
  (let [field (mapv vec (clojure.string/split-lines s))
        height (count field)
        width (count (first field))
        in-bounds? (fn [[x y]] (and (< -1 x) (< -1 y) (< x height) (< y width)))]
    (clojure.string/join
      \newline
      (map (partial apply str)
           (reduce
             add-mine
             field
             (for [x (range height)
                   y (range width)
                   neighbor (map #(mapv + [x y] %) offsets)
                   :when (in-bounds? neighbor)]
               (when (= \* (get-in field neighbor))
                 [x y])))))))
