(ns hamming)

(defn distance [from to]
  (if (= (count from) (count to))
    (let [zip (map vector from to)]
      (reduce (fn [acc e] (if (apply = e) acc (inc acc))) 0 zip))))
