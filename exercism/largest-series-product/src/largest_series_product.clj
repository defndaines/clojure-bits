(ns largest-series-product)

(defn ^:private max-product [n s]
  {:pre [(re-matches #"\p{Digit}+" s)]}
  (reduce
    (fn [acc e] (max acc (reduce * e)))
    0
    (partition n 1 (map #(Character/getNumericValue %) s))))

(defn largest-product [n s]
  {:pre [(not (neg? n))
         (<= n (count s))]}
  (if (zero? n)
    1
    (max-product n s)))
