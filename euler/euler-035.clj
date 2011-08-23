(defn rotate
 [n]
 (loop [x 1000000]
  (let [r (rem n x)]
   (if (not= n r)
    (+ (* 10 r) (/ (- n r) x))
    (recur (/ x 10))))))

(defn has?
 [n coll]
 (cond
  (empty? coll) false
  (= n (first coll)) true
  :else (recur n (rest coll))))

(defn r-list
 [n]
 (loop [coll (conj '() n)]
  (let [rot (rotate (first coll))]
   (if (has? rot coll) coll
    (recur (conj coll rot))))))

; Inefficient -> reperforms prime? work
(defn circular-prime?
 [n]
 (loop [coll (r-list n)]
  (cond
   (empty? coll) true
   (not (prime? (first coll))) false
   :else (recur (rest coll)))))

;(count (filter circular-prime? (filter prime? (range 2 1000000))))

(def primes (filter prime? (range 2 1000000)))

; This still does extra work, for each digit of the candidate
(defn c-prime?
 [n]
 (loop [coll (r-list n)]
  (cond
   (empty? coll) true
   (and (odd? (first coll)) (has? (first coll) primes)) (recur (rest coll))
   :else false)))

; Assumes an ordered list (the primes)
(defn o-has?
 [n coll]
 (cond
  (empty? coll) false
  (= n (first coll)) true
  (> (first coll) n) false
  :else (recur n (rest coll))))

; Assumes we are only checking against a known prime
(defn circular-prime?
 [n]
 (loop [coll (r-list n)]
  (cond
   (empty? coll) true
   (o-has? (first coll) primes) (recur (rest coll))
   :else false)))

(defn add-to-list
 [from coll]
 (if
  (empty? from) coll
  (cons (first from) (add-to-list (rest from) coll))))

(defn rember
 [a lat]
 (cond
  (empty? lat) '()
  (= (first lat) a) (rest lat)
  :else (cons (first lat) (rember a (rest lat)))))

(defn remove-from-list
 [sub coll]
 (if (empty? sub) coll
 (recur (rest sub) (rember (first sub) coll))))

(defn odd-digits?
 [n]
 (every? #(odd? (char-to-digit %)) (seq (str n))))

(def candidates (filter odd-digits? primes))

(defn euler-035
 [seed]
 (loop [found '() base seed]
  (if (empty? base) found
   (let [n (first base) coll (r-list n)]
    ;(println (str "Handling: " (first base)))
    (if (every? #(o-has? % base) coll) (recur (add-to-list coll found) (remove-from-list coll base))
     (recur found (rest base)))))))
