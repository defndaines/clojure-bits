(ns sum-of-multiples)

(def whole? (complement ratio?))

(defn sum-of-multiples
  ([x] (sum-of-multiples [3 5] x))
  ([of x]
   (let [pred (apply some-fn (map (fn [d] (fn [n] (whole? (/ n d)))) of))]
     (reduce + (filter pred (range 2 x))))))