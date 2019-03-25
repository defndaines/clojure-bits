(ns armstrong-numbers
  "An Armstrong number is a number that is the sum of its own digits each
  raised to the power of the number of digits.

  For example:
  * 9 is an Armstrong number, because 9 = 9^1 = 9
  * 10 is not an Armstrong number, because 10 != 1^2 + 0^2 = 1
  * 153 is an Armstrong number, because: 153 = 1^3 + 5^3 + 3^3 = 1 + 125 + 27 = 153
  * 154 is not an Armstrong number, because: 154 != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190

  See: https://en.wikipedia.org/wiki/Narcissistic_number")

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
