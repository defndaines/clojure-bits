(ns luhn)

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