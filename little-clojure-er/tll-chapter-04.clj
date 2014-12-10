; The Little LISPer (Trade Edition, 1987)
; Translated to Clojure

;Chapter 4 - Page 56
(defn plus
  "Add n and m"
  [n m]
  (cond
    (zero? m) n 
    :else (inc (plus n (dec m)))))

;Chapter 4 - Page 57
(defn subtract
  "Subtract m from n"
  [n m]
  (cond
    (zero? m) n
    :else (dec (subtract n (dec m)))))

;Chapter 4 - Page 61
(defn addvec
  "Add together the values in vex"
  [vec]
  (cond
    (null? vec) 0
    :else (+ (first vec) (addvec (rest vec)))))

;Chapter 4 - Page 61
(defn multiply
  "Multiply n by m"
  [n m]
  (cond
    (zero? m) 0
    :else (+ n (multiply n (dec m)))))

;Chapter 4 - Page 65
(defn vec+
  "Add together each numerical value of vec1 and vec2 when they are of equal size"
  [vec1 vec2]
  (cond
    (null? vec1) '()
    :else (cons (+ (first vec1) (first vec2))
        (vec+ (rest vec1) (rest vec2)))))

;Chapter 4 - Page 67
(defn vec+
  "Add together each element of vec1 and vec2, regardless of the size of each vec"
  [vec1 vec2]
  (cond
    (null? vec1) vec2
    (null? vec2) vec1
    :else (cons (+ (first vec1) (first vec2))
        (vec+ (rest vec1) (rest vec2)))))

;Chapter 4 - Page 69
(defn gt
  "True if n is greater than m"
  [n m]
  (cond
    (zero? n) false
    (zero? m) true
    :else (gt (dec n) (dec m))))

;Chapter 4 - Page 70
(defn lt
  "True if n is less than m"
  [n m]
  (cond
    (zero? m) false
    (zero? n) true
    :else (lt (dec n) (dec m))))

;Chapter 4 - Page 70
(defn eq
  "True if n is equal to m"
  [n m]
  (cond
    (gt n m) false
    (lt n m) false
    :else true))

;Chapter 4 - Page 71
;Using ** to represent exponent, since ^ isn't available to use in clojure
(defn **
  "Raise n to the power of m"
  [n m]
  (cond
    (zero? m) 1
    :else (multiply n (** n (dec m)))))

;Chapter 4 - Page 71
(defn length
  "Count the number of atoms in a lat"
  [lat]
  (cond
    (null? lat) 0
    :else (inc (length (rest lat)))))

;Chapter 4 - Page 72
(defn pick
  "Return the nth element of lat"
  [n lat]
  (cond
    (null? lat) nil
    (zero? (dec n)) (first lat)
    :else (pick (dec n) (rest lat))))

;Chapter 4 - Page 73
(defn rempick
  "Remove the nth element from lat"
  [n lat]
  (cond
    (null? lat) '()
    (zero? (dec n)) (rest lat)
    :else (cons (first lat) (rempick (dec n) (rest lat)))))

;Chapter 4 - Page 73
(defn no-nums
  "Returns a lat obtained by removing all the numbers from lat"
  [lat]
  (cond
    (null? lat) '()
    :else (cond
      (number? (first lat)) (no-nums (rest lat))
      :else (cons (first lat) (no-nums (rest lat))))))

;Chapter 4 - Page 74
(defn all-nums
  "Returns a vec obtained by removing all non-numbers from lat"
  [lat]
  (cond
    (null? lat) '()
    :else (cond
      (number? (first lat)) (cons (first lat) (all-nums (rest lat)))
      :else (all-nums (rest lat)))))

;Chapter 4 - Page 74
(defn eqan?
  "Returns true if a1 and a2 are equal"
  [a1 a2]
  (cond
    (number? a1) (cond
        (number? a2) (eq a1 a2)
        :else false)
    (number? a2) false
    :else (= a1 a2)))

