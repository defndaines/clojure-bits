(ns armstrong-numbers)

(defn digits
  "Get a sequence of digits, in order, for a given number.
  For example, given 2501, return (2 5 0 1)."
  [n]
  (loop [ds '()
         x n]
    (if (zero? x)
      ds
      (recur (conj ds (mod x 10))
             (quot x 10)))))

(defn pow-of
  "Generate a function that, when given a number, will return that number to
  the power of `n`."
  [n]
  (fn [x]
    (apply * (repeat n x))))

(defn armstrong?
  "Check if a given number is an Armstrong number, also known as a narcissistic
  number. This is a number that is the sum of its own digits each raised to the
  power of the number of digits."
  [n]
  (let [ds (digits n)
        pow-of-len (pow-of (count ds))]
    (->> ds
         (map pow-of-len)
         (apply +)
         (= n))))
