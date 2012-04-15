(declare hat)

(defn cat [n]
 (if-not (zero? n)
  (do
   (if (= 0 (rem n 100))
    (println "cat: " n))
   #(hat (dec n)))))

(defn hat [n]
 (if-not (zero? n)
  (do
   (if (= 0 (rem n 100))
    (println "hat: " n))
   #(cat (dec n)))))
