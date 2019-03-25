(ns flatten-array
  "Take a nested list and return a single flattened list with all values
  except nil.

  The challenge is to write a function that accepts an arbitrarily-deep nested
  list-like structure and returns a flattened structure without any nil
  values.")

(defn flatten [node]
  (loop [[head & tail] node
         acc []]
    (if (sequential? head)
      (recur tail (into [] (concat acc (flatten head))))
      (if (number? head)
        (recur tail (conj acc head))
        acc))))
