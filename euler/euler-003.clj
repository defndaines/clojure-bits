; prime factors of 13195 are 5, 7, 13 and;Thi 29.
;What is the largest prime factor of the number 600851475143 ?

(defn get-prime-factors
  ([nmbr] (get-prime-factors 2 nmbr))
  ([seed nmbr]
    (cond
      (<= nmbr seed) `(~nmbr)
      (zero? (rem nmbr seed))
        (cons seed (get-prime-factors seed (/ nmbr seed)))
      :else (get-prime-factors (inc seed) nmbr))))

;Answer = 6857

;And, as a bonus:

(defn prime?
 [x]
 (= 1 (count (get-prime-factors x))))


;This is from the Internet for discovering primes
(defn primes
 "Sieve of Eratosthenes. [ http://en.wikipedia.org/wiki/Sieve_of_Eratosthenes#Algorithm ]"
 [n]
 (loop [p 2 seq (range 2 n)]
  (if (> (* p p) n)
   seq
   (let [new-seq (filter #(or (= % p) (not= 0 (mod % p))) seq)
         new-p (first (filter #(> % p) new-seq))]
    (recur new-p new-seq)))))

;Optimized version of what I was doing, since it couldn't use (recur)
(defn divisible? [num by] (zero? (rem num by)))
   
(defn prime-factors [num]
 (loop [i 2, num num, factors '()]
  (cond
   (> i num) factors
   (divisible? num i) (recur i (/ num i) (conj factors i))
   :else (recur (inc i) num factors))))

(defn prime?
 [x]
 (= 1 (count (prime-factors x))))

