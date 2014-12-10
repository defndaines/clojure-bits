; The Little LISPer (Trade Edition, 1987)
; Translated to Clojure

; Chapter 2 - Page 15
(defn lat?
  "Evaluate whether list l contains only atoms"
  [l]
  (cond
    (null? l) true
    (atom? (first l)) (lat? (rest l))
    :else false))

; Chapter 2 - Page 22
(defn member?
  "Evaluate whether a is a member of the list of atoms lat"
  [a lat]
  (cond
    (null? lat) nil
    :else (or
      (= (first lat) a)
      (member? a (rest lat)))))

