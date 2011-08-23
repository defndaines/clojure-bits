; A Pythagorean triplet is a set of three natural numbers, a  < b  < c, for which,
; a^(2) + b^(2) = c^(2)
; For example, 3^(2) + 4^(2) = 9 + 16 = 25 = 5^(2).
; There exists exactly one Pythagorean triplet for which a + b + c = 1000.
; Find the product abc.

; Using the Rutger's erowland method
; a = (+ (* u u) (* 2 u v))
; b = (+ (* 2 v v) (* 2 u v))
; c = (+ (* u u) (* 2 v v) (* 2 u v))
; and g = (* u u)
;     h = (* 2 v v)
;     i = (* 2 u v)
; so (+ a b c) = (+ g g h h i i i)

; http://www.math.rutgers.edu/~erowland/pythagoreantriples.html
; Also, can assume (> v (Math/sqrt 500))
(defn matching-u-v?
 [u v]
 (= 500 (+ (* u u) (* 2 v v) (* 3 u v))))

(defn euler-009
 ([] (euler-009 1 1))
 ([u v]
  (cond
   (matching-u-v? u v) (* (+ (* u u) (* 2 u v)) (+ (* 2 v v) (* 2 u v)) (+ (* u u) (* 2 v v) (* 2 u v)))
   (> v 22) (recur (inc u) 1)
   :else (recur u (inc v)))))

