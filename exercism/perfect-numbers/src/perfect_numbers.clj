(ns perfect-numbers
  "Determine if a number is perfect, abundant, or deficient based on
  Nicomachus' (60 - 120 CE) classification scheme for natural numbers.

  The Greek mathematician Nicomachus devised a classification scheme for
  natural numbers, identifying each as belonging uniquely to the categories of
  perfect, abundant, or deficient based on their aliquot sum. The aliquot sum
  is defined as the sum of the factors of a number not including the number
  itself.

  * Perfect: aliquot sum = number
  * Abundant: aliquot sum > number
  * Deficient: aliquot sum < number")

(defn- factors [n]
  (filter #(zero? (rem n %)) (range 1 n)))

(defn classify [n]
  (let [sum (reduce + (factors n))]
    (cond
      (zero? sum) (throw (IllegalArgumentException.))
      (> sum n) :abundant
      (= sum n) :perfect
      (< sum n) :deficient)))
