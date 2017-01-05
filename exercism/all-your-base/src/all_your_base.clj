(ns all-your-base)

;; Naive implementation required intermediate conversion to decimal.

(defn- pow [n x]
  (apply * (repeat x n)))

(defn- ->decimal [base digits]
  (reduce
    (fn [acc [i d]]
      (+ acc (* d (pow base i))))
    0
    (map-indexed vector (reverse digits))))

(defn- drop-leading-zeroes [s]
  (let [digits (drop-while zero? s)]
    (if (seq digits)
      digits
      '(0))))

(defn- <-decimal [base n]
  (loop [n n acc [] pos 6]
    (if (zero? pos) (drop-leading-zeroes (conj acc n))
      (let [x (pow base pos)]
        (recur
          (rem n x)
          (conj acc (quot n x))
          (dec pos))))))

(defn convert [base-of digits base-to]
  (when (and (> base-of 1) (> base-to 1) (seq digits) (every? #(> base-of % -1) digits))
    (<-decimal base-to
               (->decimal base-of digits))))