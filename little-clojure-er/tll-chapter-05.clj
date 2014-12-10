; The Little LISPer (Trade Edition, 1987)
; Translated to Clojure

;Chapter 5 - Page 78
(defn multirember
  "Returns a lat with all occurrences of a removed"
  [a lat]
  (cond
    (null? lat) '()
    :else (cond
      (= (first lat) a)
          (multirember a (rest lat))
      :else (cons (first lat)
          (multirember a (rest lat))))))

;Chapter 5 - Page 81
(defn multiinsertR
  "Builds a lat with new inserted to the right of every occurrence of old"
  [new old lat]
  (cond
    (null? lat) '()
    :else (cond
      (= (first lat) old)
          (cons (first lat) (cons new (multiinsertR new old (rest lat))))
      :else (cons (first lat) (multiinsertR new old (rest lat))))))

;Chapter 5 - Page 82
(defn multiinsertL
  "Builds a lat with new inserted to the left of every occurrence of old"
  [new old lat]
  (cond
    (null? lat) '()
    :else (cond
      (= (first lat) old)
          (cons new (cons old (multiinsertL new old (rest lat))))
      :else (cons (first lat) (multiinsertL new old (rest lat))))))

;Chapter 5 - Page 83
(defn multisubst
  "Replace every occurrence of old in the lat with the atom new"
  [new old lat]
  (cond
    (null? lat) '()
    :else (cond
      (= (first lat) old)
          (cons new (multisubst new old (rest lat)))
      :else (cons (first lat) (multisubst new old (rest lat))))))

;Chapter 5 - Page 83
(defn occur
  "Count the number of times an atom a appears in a lat"
  [a lat]
  (cond
    (null? lat) 0
    :else (cond
      (= (first lat) a) (inc (occur a (rest lat)))
      :else (occur a (rest lat)))))

;Chapter 5 - Page 84
(defn one?
  "True if n is 1"
  [n]
  (cond
    (zero? n) false
    :else (zero? (dec n))))

;or simplified ...
(defn one?
  "True if n is 1"
  [n]
  (= n 1))

;Chapter 5 - Page 84
(defn rempick
  "Removes the nth atom from the lat"
  [n lat]
  (cond
    (null? lat) '()
    (one? n) (rest lat)
    :else (cons (first lat) (rempick (dec n) (rest lat)))))

