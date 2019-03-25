(ns luhn
  "Given a number determine whether or not it is valid per the Luhn formula.

  The Luhn algorithm is a simple checksum formula used to validate a variety
  of identification numbers, such as credit card numbers and Canadian Social
  Insurance Numbers.

  See https://en.wikipedia.org/wiki/Luhn_algorithm")

(defn l-double [x]
  (let [n (* 2 x)]
    (if (> 10 n)
      n
      (- n 9))))

(defn checksum [x]
  (let [digits (map #(Character/getNumericValue %) (reverse (str x)))
        chunks (partition 2 2 [0] digits)]
    (rem (reduce + (flatten (for [[a b] chunks] [a (l-double b)]))) 10)))

(defn valid? [x]
  (zero? (checksum x)))

(defn add-check-digit [x]
  (let [n (* 10 x)]
    (+ n (- 10 (checksum n)))))
