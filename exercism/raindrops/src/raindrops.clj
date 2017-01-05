(ns raindrops)

(defn of [n]
  (loop [x n f 2 acc []]
    (if (= 1 x)
      acc
      (if (ratio? (/ x f))
        (recur x (inc f) acc)
        (recur (/ x f) f (conj acc f))))))

(def rain {3 "Pling"
           5 "Plang"
           7 "Plong"})

(defn convert [n]
  (let [prime-factors (of n)
        drops (distinct (filter #{3 5 7} prime-factors))]
    (if (seq drops)
      (apply str (map #(get rain %) drops))
      (str n))))