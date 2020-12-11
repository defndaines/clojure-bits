(ns aoc.day-11
  "--- Day 11: Seating System ---
  ...
  Simulate your seating area by applying the seating rules repeatedly until no
  seats change state. How many seats end up occupied?")

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
  [seats pos]
  (when (= \# (get-in seats pos))
    (when (< 3 (count (filter #(= \# %) (adjacent seats pos))))
      pos)))

(defn- replace-char
  [seats pos ch]
  (let [[x y] pos]
    (update seats x #(str (subs % 0 y) ch (subs % (inc y))))))

(defn round
  [seating]
  (let [all-positions (for [x (range (count seating))
                            y (range (count (first seating)))]
                        [x y])
        to-occupy (keep #(occupy-seat seating %) all-positions)
        to-abandon (keep #(abandon-seat seating %) all-positions)]
    (reduce
      (fn [acc e] (replace-char acc e \L))
      (reduce (fn [acc e] (replace-char acc e \#)) seating to-occupy)
      to-abandon)))

(defn occupied
  [seating]
  (reduce
    (fn [acc e] (+ acc (count (filter #(= \# %) e))))
    0
    seating))

(defn stabilize
  [seating]
  (loop [seating seating]
    (let [step (round seating)]
      (if (= seating step)
        step
        (recur step)))))
