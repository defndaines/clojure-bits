(defn char-to-digit
  [ch]
  (- (int ch) (int \0)))

; Lazy fibonacci sequence
(defn fibo []
  (map first (iterate (fn [[a b]] [b (+ a b)]) [0 1])))

; Lazy triangle numbers
(defn tri-n
  [n]
  (* n (inc n) (/ 1 2)))
(def tri-seq (map tri-n (iterate inc 0)))

; Divisors
(defn divisible?
  [n d]
  (zero? (rem n d)))

(defn divisors
  [x]
  (let [div-for-x? (fn [d] (divisible? x d))]
    (filter div-for-x? (range 1 (inc (/ x 2))))))

; Primes
(defn prime-factors [num]
  (loop [i 2, num num, factors '()]
    (cond
      (> i num) factors
      (divisible? num i) (recur i (/ num i) (conj factors i))
      :else (recur (inc i) num factors))))

(defn prime?
  [n]
  (cond
    (or (= n 2) (= n 3)) true
    (even? n) false
    :else (let [sqrt-n (Math/sqrt n)]
            (loop [i 3]
              (cond
                (divisible? n i) false
                (< sqrt-n i)     true
                :else            (recur (+ i 2)))))))

(defn factorial
  [n]
  (loop [cnt n acc 1]
    (if (zero? cnt) acc
      (recur (dec cnt) (* acc cnt)))))
