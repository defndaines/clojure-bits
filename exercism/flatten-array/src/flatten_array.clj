(ns flatten-array)

(defn flatten [node]
  (loop [[head & tail] node
         acc []]
    (if (sequential? head)
      (recur tail (into [] (concat acc (flatten head))))
      (if (number? head)
        (recur tail (conj acc head))
        acc))))
