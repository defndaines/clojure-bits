(ns aoc.day-11
  "--- Day 11: Seating System ---
  ...
  Simulate your seating area by applying the seating rules repeatedly until no
  seats change state. How many seats end up occupied?
  --- Part Two ---
  ...
  Given the new visibility method and the rule change for occupied seats
  becoming empty, once equilibrium is reached, how many seats end up
  occupied?")

(defn- adjacent
  [seats pos]
  (let [[x y] pos]
    (for [m [(dec x) x (inc x)]
          n [(dec y) y (inc y)]
          :when (not (and (= x m) (= y n)))]
      (get-in seats [m n]))))

(defn- occupy-seat
  [seats pos]
  (when (= \L (get-in seats pos))
    (when-not (seq (filter #(= \# %) (adjacent seats pos)))
      pos)))

(defn- abandon-seat
  [seats threshold pos]
  (when (= \# (get-in seats pos))
    (when (<= threshold (count (filter #(= \# %) (adjacent seats pos))))
      pos)))

(defn- replace-char
  [seats pos ch]
  (let [[x y] pos]
    (update seats x #(str (subs % 0 y) ch (subs % (inc y))))))

(defn round
  [seats]
  (let [all-positions (for [x (range (count seats))
                            y (range (count (first seats)))]
                        [x y])
        to-occupy (keep #(occupy-seat seats %) all-positions)
        to-abandon (keep #(abandon-seat seats 4 %) all-positions)]
    (reduce
      (fn [acc e] (replace-char acc e \L))
      (reduce (fn [acc e] (replace-char acc e \#)) seats to-occupy)
      to-abandon)))

(defn occupied
  [seats]
  (reduce
    (fn [acc e] (+ acc (count (filter #(= \# %) e))))
    0
    seats))

(defn stabilize
  [seats]
  (loop [seats seats]
    (let [step (round seats)]
      (if (= seats step)
        step
        (recur step)))))

(def directions
  [[-1 -1] [0 -1] [1 -1]
   [-1  0]        [1  0]
   [-1  1] [0  1] [1  1]])

(defn- line-of-sight
  [seats pos dir]
  (loop [pos pos]
    (let [view (map + pos dir)
          ch (get-in seats view)]
      (case ch
        nil nil
        \. (recur view)
        view))))

(defn in-view
  [seats pos]
  (keep (partial line-of-sight seats pos) directions))
