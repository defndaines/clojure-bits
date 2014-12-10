; The Little LISPer (Trade Edition, 1987)
; Translated to Clojure

; Chapter 3 - Page 37
(defn rember
  "Return a new list of atoms, removing the first occurrence of a from lat"
  [a lat]
  (cond
    (null? lat) '()
    :else (cond
      (= (first lat) a) (rest lat)
      :else (cons (first lat) (rember a (rest lat))))))

; Chapter 3 - Page 41 (rewrite rember)
(defn rember
  "Return a new list of atoms, removing the first occurrence of a from lat"
  [a lat]
  (cond
    (null? lat) '()
    (= (first lat) a) (rest lat)
    :else (cons (first lat) (rember a (rest lat)))))

; Chapter 3 - Page 46
(defn firsts
  "Takes a list which must be either null or contain only non-null lists and builds a list composed of the first S-expression of each internal list"
  [l]
  (cond
    (null? l) '()
    :else (cons (first (first l)) (firsts (rest l)))))

; Chapter 3 - Page 51
(defn insertR
  "Builds a lat with new inserted to the right of the first occurrence of old"
  [new old lat]
  (cond
    (null? lat) '()
    :else (cond
      (= (first lat) old)
        (cons old (cons new (rest lat)))
      :else (cons (first lat) (insertR new old (rest lat))))))

;Chapter 3 - Page 52
(defn insertL
  "Builds a lat with new inserted to the left of the first occurrence of old"
  [new old lat]
  (cond
    (null? lat) '()
    :else (cond
      (= (first lat) old)
        (cons new (cons old (rest lat)))
      :else (cons (first lat) (insertL new old (rest lat))))))

;Chapter 3 - Page 52
(defn subst
  "Replace the first occurrence of old in the lat with the atom new"
  [new old lat]
  (cond
    (null? lat) '()
    :else (cond
      (= (first lat) old)
        (cons new (rest lat))
      :else (cons (first lat) (subst new old (rest lat))))))

;Chapter 3 - Page 53
(defn subst2
  "Replace the first occurrence of o1 or the first occurrence of o2 with new"
  [new o1 o2 lat]
  (cond
    (null? lat) '()
    :else (cond
      (= (first lat) o1)
        (cons new (rest lat))
      (= (first lat) o2)
        (cons new (rest lat))
      :else
        (cons (first lat) (subst2 new o1 o2 (rest lat))))))

