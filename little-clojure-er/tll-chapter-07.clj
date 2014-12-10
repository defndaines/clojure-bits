; The Little LISPer (Trade Edition, 1987)
; Translated to Clojure

;Chapter 7 - Page 107
;NOTE: ^ is reserved in Clojure, so using "**"
(defn numbered?
  "Determine whether a representation of an arithmetic expression only contains numbers besides the +, *, and **"
  [aexp]
  (cond
    (atom? aexp) (number? aexp)
    (= (first (rest aexp)) '+)
        (and (numbered? (first aexp))
             (numbered? (first (rest (rest aexp)))))
    (= (first (rest aexp)) '*)
        (and (numbered? (first aexp))
             (numbered? (first (rest (rest aexp)))))
    (= (first (rest aexp)) '**) 
        (and (numbered? (first aexp))
             (numbered? (first (rest (rest aexp)))))))

;Chapter 7 - Page 107
;This is the "simplified" version, but it doesn't validate +, *, or **
(defn numbered?
  "Determine whether a representation of an arithmetic expression only contains numbers in the first and third positions"
  [aexp]
  (cond
    (atom? aexp) (number? aexp)
    :else (and
        (numbered? (first aexp))
        (numbered? (first (rest (rest aexp)))))))

;Chapter 7 - Page 109
(defn value
  "Return the natural value of a numbered arithmetic expression"
  [aexp]
  (cond
    (number? aexp)
      aexp
    (= (first (rest aexp)) '+)
      (+ (value (first aexp)) (value (first (rest (rest aexp)))))
    (= (first (rest aexp)) '*)
      (* (value (first aexp)) (value (first (rest (rest aexp)))))
    :else
      (** (value (first aexp)) (value (first (rest (rest aexp)))))))
      
;Chapter 7 - Page 112
(defn first-sub-exp
  "Get the first sub expression of an arithmetic expression"
  [aexp]
  (first (rest aexp)))

(defn second-sub-exp
  "Get the second sub expression of an arithmetic expression"
  [aexp]
  (first (rest (rest aexp))))

(defn operator
  "Get the operator of an arithmetic expression"
  [aexp]
  (first aexp))

;Chapter 7 - Page 113
(defn value
  "Return the natural value of a numbered arithmetic expression"
  [aexp]
  (cond
    (number? aexp) aexp
    (= (operator aexp) 'plus)
      (+ (value (first-sub-exp aexp)) (value (second-sub-exp aexp)))
    (= (operator aexp) 'times)
      (* (value (first-sub-exp aexp)) (value (second-sub-exp aexp)))
    :else
      (** (value (first-sub-exp aexp)) (value (second-sub-exp aexp)))))

;Chapter 7 - Page 113
(defn first-sub-exp
  "Get the first sub expression of an arithmetic expression"
  [aexp]
  (first aexp))

(defn operator
  "Get the operator of an arithmetic expression"
  [aexp]
  (first (rest aexp)))

;Chapter 7 - Page 114
(defn empty-list?
  "Test for the null list"
  [s]
  (= s '()))

;Chapter 7 - Page 115
(defn is-zero?
  "Test for representation of zero"
  [n]
  (empty-list? n))

(defn add1
  "Add 1 to value n"
  [n]
  (cons '() n))

(defn sub1
  "Subtract 1 from n"
  [n]
  (rest n))

(defn plus
  "Add together n and m"
  [n m]
  (cond
    (is-zero? m) n
    :else (add1 (plus n (sub1 m)))))

;Chapter 7 - Page 116
(defn is-number?
  "Determine if n is a number"
  [n]
  (cond
    (empty-list? n) true
    :else (and
      (empty-list? (first n))
      (is-number? (rest n)))))

;Chapter 7 - Page 117
(defn cookies
  "Make cookies"
  (bake
    '(350 degrees)
    '(12 minutes)
    (mix
      '(walnuts 1 cup)
      '(chocolate-chips 16 ounces)
      (mix
        (mix
          '(flour 2 cups)
          '(oatmeal 2 cups)
          '(salt .5 teaspoon)
          '(baking-powder 1 teaspoon)
          '(baking-soda 1 teaspoon))
        (mix
          '(eggs 2 large)
          '(vanilla 1 teaspoon)
          (cream
            '(butter 1 cup)
            '(sugar 2 cups)))))))
