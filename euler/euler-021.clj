; Note - Smalled pair of amicable numbers is (220, 2840
; The first few amicable pairs are: (220, 284), (1184, 1210), (2620, 2924), (5020, 5564), (6232, 6368)
; both numbers should be even or odd
(defn divisible?
 [n d]
 (zero? (rem n d)))

(defn divisors
 [x]
 (let [div-for-x? (fn [d] (divisible? x d))]
  (filter div-for-x? (range 1 (inc (/ x 2))))))

(defn sum-of-divisors
 [x]
 (reduce + (divisors x)))

(defn amicable?
 [a b]
 (and (= a (sum-of-divisors b)) (= b (sum-of-divisors a))))

(defn ami-to-x?
 [x]
 (fn [y] (amicable? x y)))
