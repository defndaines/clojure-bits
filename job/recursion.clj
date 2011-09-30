; 8.1 Write a method to generate the nth Fibonacci number.

(defn fibo []
 (map first (iterate (fn [[a b]] [b (+ a b)]) [0 1])))

; From PrCl — this specifically returns the nth, where previous is just the seq
(defn recur-fibo [n]
 (letfn [(fib
          [current next n]
          (if (zero? n)
            current
            (recur next (+ current next) (dec n))))]
  (fib 0 1 n)))


; 8.2 Imagine a robot sitting on the upper left hand corner of an NxN grid.
;     The robot can only move in two directions: right and down. How many
;     possible paths are there for the robot?
;     FOLLOW UP
;     Imagine certain squares are “off limits”, such that the robot can not
;     step on them. Design an algorithm to get all possible paths for the robot.

(defn factorial [n]
 (loop [cnt n acc 1]
  (if (zero? cnt) acc
      (recur (dec cnt) (* acc cnt)))))

(defn path-count [n]
 (/ (factorial (+ (dec n) (dec n))) (* (factorial (dec n)) (factorial (dec n)))))

;; Just adding some data points to use for examples.
(def blocked '([2 3] [5 3]))

(defn points-equal?
 [m n]
 (and (= (first m) (first n)) (= (second m) (second n))))

(defn blocked?
 [point]
 (loop [to-cmp blocked]
  (cond (empty? to-cmp) false
        (points-equal? (first to-cmp) point) true
        :else (recur (rest to-cmp)))))

(defn inc-x
 [point]
 (vector (inc (first point)) (second point)))

(defn inc-y
 [point]
 (vector (first point) (inc (second point))))

(defn out-of-bounds?
 [limit point]
 (or (> (first point) limit) (> (second point) limit)))

(defn get-paths
 ([n] (get-paths n [0 0] '()))
 ([n point path]
  (cond (points-equal? (vector n n) point) (list (reverse (conj path point)))
        (out-of-bounds? n point) nil
        (blocked? point) nil
        :else (concat (get-paths n (inc-y point) (conj path point))
                    (get-paths n (inc-x point) (conj path point))))))


; 8.3 Write a method that returns all subsets of a set.
;; Note: The sexy code from combinatorics gets the indices for each combination
;; Also, personal note. First attempt (to shrink down incrementally by one)
;;   would lead to a lot of duplication.

(defn combinations
 [coll n]
 (cond
  (zero? n) (list ())
  (= n (count coll)) (list coll)
  (= n 1) (partition n coll)
  :else (concat
         (map #(concat (list (first coll)) %) (combinations (rest coll) (dec n)))
         (combinations (rest coll) n))))

(defn subsets
 [coll]
 (reduce concat (for [n (range (inc (count coll)))] (combinations coll n))))

; 8.4 Write a method to compute all permutations of a string.
;; (join coll) is doing (apply str coll)
(defn rm-from-str
 [word c]
 (apply str
  (loop [mot word acc '[]]
   (cond (empty? mot) acc
         (= c (first mot)) (concat acc (rest mot))
         :else (recur (rest mot) (conj acc (first mot)))))))
