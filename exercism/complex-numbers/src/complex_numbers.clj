(ns complex-numbers)

(defn real [[a _]] a)

(defn imaginary [[_ b]] b)

(defn abs [[a b]]
  (Math/sqrt (+ (* a a) (* b b))))

(defn conjugate [[a b]]
  [a (* -1 b)])

(defn add [x y]
  (map + x y))

(defn sub [x y]
  (map - x y))

(defn mul [[a b] [c d]]
  [(- (* a c) (* b d))
   (+ (* b c) (* a d))])

(defn div [[a b] [c d]]
  [(float (/ (+ (* a c) (* b d))
             (+ (* c c) (* d d))))
   (float (/ (- (* b c) (* a d))
             (+ (* c c) (* d d))))])
