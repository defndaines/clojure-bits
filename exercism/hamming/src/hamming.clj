(ns hamming
  "Calculate the Hamming difference between two DNA strands.

  The Hamming distance is only defined for sequences of equal length, so an
  attempt to calculate it between sequences of different lengths should not
  work.")

(defn distance [from to]
  (if (= (count from) (count to))
    (let [zip (map vector from to)]
      (reduce (fn [acc e] (if (apply = e) acc (inc acc))) 0 zip))))
