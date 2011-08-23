(defn multiple-of-3 [x] (= 0 (mod x 3)))
; More efficient as:
(defn multiple-of-3 [x] (zero? (rem x 3)))

(defn multiple-of-5 [x] (= 0 (mod x 5)))
; More efficient as:
(defn multiple-of-5 [x] (zero? (rem x 5)))

; Answer:
(reduce + (filter (fn [x] (or (multiple-of-3 x) (multiple-of-5 x))) (range 3 1000)))

;Fast similar solution from Internet
(time (reduce + (filter #(or (zero? (rem % 3)) (zero? (rem % 5))) (range 1000))))

;Fastest from Internet?
(time (+ (apply + (range 3 1000 3)) (apply + (range 5 1000 5)) (- (apply + (range 15 1000 15)))))
