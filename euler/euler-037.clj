(defn truncate-l
 [n]
 (loop [x 1000000]
  (let [r (rem n x)]
   (if (not= n r)
    r
    (recur (/ x 10))))))

(defn truncate-r
 [n]
 (/ (- n (rem n 10)) 10))

(defn left-list
 [n]
 (let [x (truncate-l n)]

