(ns euler-282.core)

;; A(m, n) -> n + 1                 if m = 0
;;         -> A(m - 1, 1)           if m > 0 and n = 0
;;         -> A(m - 1, A(m, n - 1)) if M > 0 and n > 0

;; Find sum n 0..6 A(n, n) and give answer mod 14^8

(defn exp [x n]
  (loop [acc 1 n n]
    (if (zero? n) acc
      (recur (*' x acc) (dec n)))))

(defn acker [m n]
 (cond
   (= 0 m) (+' n 1)
   (= 1 m) (+' n 2)
   (= 2 m) (+' (*' 2 n) 3)
   (= 3 m) (-' (exp 2 (+' n 3)) 3)
   (and (> m 0) (= 0 n)) (recur (dec m) 1)
   :else (recur (dec m) (acker m (dec n)))))

(defn acker [m n]
 (cond
   (= 0 m) (inc n)
   (and (> m 0) (= 0 n)) (acker (dec m) 1)
   :else (acker (dec m) (acker m (dec n)))))

(def ackermemo (memoize acker))
