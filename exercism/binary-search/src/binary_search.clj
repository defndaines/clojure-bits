(ns binary-search
  "Implement a binary search algorithm.")

(defn middle [v]
  (quot (count v) 2))

(defn search-for [n coll]
  (loop [from 0
         v (vec coll)]
    (assert (not (empty? v)) "not found")
    (let [m (middle v)
          x (get v m)]
      (cond
        (= n x) (+ from m)
        (> n x) (recur (+ from (inc m)) (subvec v (inc m)))
        :else (recur from (subvec v 0 m))))))
