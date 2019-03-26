(defn digits
  "Given a number, `n`, return a sequence of its digits. If a `base` is
  provided, convert to that base, otherwise base 10 (decimal)."
  ([n] (digits n 10))
  ([n base]
   (when (< 1 base)
     (loop [acc '() x n]
       (if (= 0 x)
         acc
         (recur (cons (rem x base) acc) (quot x base)))))))
