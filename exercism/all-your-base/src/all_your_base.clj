(ns all-your-base
  "Convert a number, represented as a sequence of digits in one base, to any
  other base.

  Implement general base conversion. Given a number in base a, represented as
  a sequence of digits, convert it to base b.")

;; Naive implementation required intermediate conversion to decimal.

(defn- pow
  "Returns the value of the first argument raised to the power of the second
  argument. Unlike Math/pow, this works with integer values."
  [n x]
  (apply * (repeat x n)))

(defn- ->decimal [base digits]
  "Given a sequence of `digits` in the provided `base`, convert to a base-10
  (aka, decimal) number.

  Warning, does not check that the digits are valid for the given base."
  (reduce
    (fn [acc [i d]]
      (+ acc (* d (pow base i))))
    0
    (map-indexed vector (reverse digits))))

(defn- drop-leading-zeroes
  "Drop leading 0s from a sequence of digits.

  For example, '(0 0 2 4 3 0) becomes '(2 4 3 0)."
  [s]
  (let [digits (drop-while zero? s)]
    (if (seq digits)
      digits
      '(0))))

(defn- <-decimal [base n]
  "Convert a decimal number `n` to the provided `base` up to a maximum of six
  digits."
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
