(defn count-downr [n]
 (if-not (zero? n)
  (do
   (if (= 0 (rem n 100))
    (println "count-down: " n))
   (recur (dec n)))))

