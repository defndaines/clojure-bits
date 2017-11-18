(ns matrix
  "Clojure implementation of matrix math, mostly to prove to myself that I
  understand how matrix multiplication works.")

(def three-by-two
  [[1 2]
   [3 4]
   [5 6]])

(def two-by-four
  [[ 7  8  9 10]
   [11 12 13 14]])

(def result
  [[ 29  32  35  38]
   [ 65  72  79  86]
   [101 112 123 134]])


(defn transpose
  "Transpose a matrix."
  [matrix]
  (apply (partial mapv vector) matrix))


(defn multiply
  "Multiply two matricies."
  [matrix-x matrix-y]
  (let [matrix-y' (transpose matrix-y)]
    (loop [[x & xs] matrix-x
           result []]
      (if (seq x)
        (recur xs
               (->> matrix-y'
                    (map (partial map * x))
                    (mapv (partial apply +))
                    (conj result)))
        result))))


(multiply three-by-two two-by-four)

(= result
   (multiply three-by-two two-by-four))
