; The Little LISPer (Trade Edition, 1987)
; Translated to Clojure

;Chapter 9 - Page 134
(defn rember-f
  "Use test? to remove a from l"
  [test? a l]
  (cond
    (null? l) '()
    (test? (first l) a) (rest l)
    :else (cons (first l) (rember-f test? a (rest l)))))

;Chapter 9 - Page 135
(defn eq?-c
  "Create a function which checks whether a value passed to it is equal to a"
  [a]
  (fn [x] (= x a)))

;Chapter 9 - Page 137
(defn rember-f
  "Create a function which will remove the value a from l based upon test?"
  [test?]
  (fn [a l]
    (cond
      (null? l) '()
      (test? (first l) a) (rest l)
      :else (cons (first l) ((rember-f test?) a (rest l))))))

;Chapter 9 - Page 138
(defn insertL-f
  "Create a function which builds a lat with new inserted to the left of the first occurence of old, using test?"
  [test?]
  (fn [new old lat]
    (cond
      (null? lat) '()
      (test? (first lat) old)
          (cons new (cons old (rest lat)))
      :else (cons (first lat) ((insertL-f test?) new old (rest lat))))))

(defn insertR-f
  "Create a function which builds a lat with new inserted to the right of the first occurrence of old, using test?"
  [test?]
  (fn [new old lat]
    (cond
      (null? lat) '()
      (test? (first lat) old)
          (cons old (cons new (rest lat)))
      :else (cons (first lat) ((insertR-f test?) new old (rest lat))))))

;Chapter 9 - Page 139
(defn seqL
  "Cons new onto the cons of old onto l"
  [new old l]
  (cons new (cons old l)))

(defn seqR
  "Cons old onto the cons of new onto l"
  [new old l]
  (cons old (cons new l)))

;Chapter 9 - Page 140
(defn insert-g
  "Builds a lat with new inserted next to old based upon the use of seqO"
  [seqO]
  (fn [new old l]
    (cond
      (null? l) '()
      (= (first l) old) (seqO new old (rest l))
      :else (cons (first l) ((insert-g seqO) new old (rest l))))))

;Chapter 9 - Page 140
(def insertL (insert-g (fn [new old l] (cons new (cons old l)))))

;Chapter 9 - Page 141
(defn seqS
  "Substitute new for old in l"
  [new old l]
  (cons new l))

(def subst (insert-g seqS))

(defn seqrem [new old l] l)
(defn rember [a l] ((insert-g seqrem) nil a l))

;Chapter 9 - Page 142
(defn atom-to-function
  "Take an argument x and return the corresponding mathematical function"
  [x]
  (cond
    (= x '+) plus
    (= x '*) multiply
    (= x '**) **))

;Chapter 9 - Page 143
(defn value
  "Return the natural value of a numbered arithmetic expression"
  [aexp]
  (cond
    (number? aexp) aexp
    :else ((atom-to-function (operator aexp)) (value (first-sub-exp aexp)) (value (second-sub-exp aexp)))))

;Chapter 9 - Page 144
(defn set-f?
  "Function to generate subset? and intersect?"
  [logical? const]
  (fn [set1 set2]
    (cond
      (null? set1) const
      :else (logical?
        (member? (first set1) set2)
        ((set-f? logical? const) (rest set1) set2)))))

(defn and-prime
  "Implement (and) as a function"
  [x y]
  (and x y))

(defn or-prime
  "Implement (or) as a function"
  [x y]
  (or x y))

;So open question is how to pass in (or) or (and)

;Chapter 9 - Page 146
(defn or-prime
  [x set1 set2]
  (or x (intersect? (rest set1) set2)))

(defn and-prime
  [x set1 set2]
  (and x (subset? (rest set1) set2)))

(defn set-f?
  [logical? const]
  (fn [set1 set2]
    (cond
      (null? set1) const
      :else (logical?
        (member? (first set1) set2)
        set1 set2))))

;Chapter 9 - Page 147
;Tighten up multirember
(defn multirember
  "Return a lat with all occurences of a removed"
  [a lat]
  (cond
    (null? lat) '()
    (= (first lat) a)
        (multirember a (rest lat))
    :else (cons (first lat) (multirember a (rest lat)))))

;Chapter 9 - Page 148
(defn curry-maker
  [future]
  (fn [l]
    (cond
      (null? l) '()
      (= (first l) 'curry) ((curry-maker future) (rest l))
      :else (cons (first l) ((curry-maker future) (rest l))))))

;Chapter 9 - Page 149
(defn function-maker
  [future]
  (fn [l]
    (cond
      (null? l) '()
      (= (first l) 'curry) ((future future) (rest l))
      :else (cons (first l) ((future future) (rest l))))))

;Chapter 9 - Page 152
(defn M
  [recfun]
  (fn [l]
    (cond
      (null? l) '()
      (= (first l) 'curry) (recfun (rest l))
      :else (cons (first l) (recfun (rest l))))))

;Chapter 9 - Page 153
; The applicative-order Y-combinator
(defn Y
  [M]
  ((fn [future]
    (M (fn [arg]
        ((future future) arg))))
   (fn [future]
    (M (fn [arg]
        ((future future) arg))))))

;Chapter 9 - Page 154
(defn L
  [recfun]
  (fn [l]
    (cond
      (null? l) 0
      :else (inc (recfun (rest l))))))

; without L
(def length (Y (fn [recfun] (fn [l] (cond (null? l) 0 :else (inc (recfun (rest l))))))))

