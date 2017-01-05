(ns triangle)

(defn type [& sides]
  (let [[a b c] (sort sides)]
    (cond
      (= a b c) :equilateral
      (<= (+ a b) c) :illogical 
      (= b c) :isosceles 
      :else :scalene)))