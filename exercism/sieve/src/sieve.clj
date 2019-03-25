(ns sieve
  "Use the Sieve of Eratosthenes to find all the primes from 2 up to a given
  number.

  The Sieve of Eratosthenes is a simple, ancient algorithm for finding all
  prime numbers up to any given limit. It does so by iteratively marking as
  composite (i.e., not prime) the multiples of each prime, starting with the
  multiples of 2. It does not use any division or remainder operation.")

(defn sieve [n]
  (loop [prime []
         remain (range 2 (inc n))]
    (if-let [check (first remain)]
      (recur (conj prime check) (filter #(ratio? (/ % check)) (rest remain)))
      prime)))
