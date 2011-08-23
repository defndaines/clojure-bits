; If the numbers 1 to 5 are written out in words: one, two, three, four, five, then there are 3 + 3 + 5 + 4 + 4 = 19 letters used in total.
; If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words, how many letters would be used?
; NOTE: Do not count spaces or hyphens. For example, 342 (three hundred and forty-two) contains 23 letters and 115 (one hundred and fifteen) contains 20 letters. The use of "and" when writing out numbers is in compliance with British usage.

(defn parse-to-word
 [x]
 (cond
  (zero? x) ""
  (= 1000 x) "onethousand"
  (> x 99)
   (if (zero? (rem x 100)) (str (parse-to-word (quot x 100)) "hundred")
       (str (parse-to-word (quot x 100)) "hundredand" (parse-to-word (rem x 100))))
  (> x 19)
   (let [tens (quot x 10)]
    (cond
     (= 2 tens) (str "twenty" (parse-to-word (rem x 10)))
     (= 3 tens) (str "thirty" (parse-to-word (rem x 10)))
     (= 4 tens) (str "forty" (parse-to-word (rem x 10)))
     (= 5 tens) (str "fifty" (parse-to-word (rem x 10)))
     (= 6 tens) (str "sixty" (parse-to-word (rem x 10)))
     (= 7 tens) (str "seventy" (parse-to-word (rem x 10)))
     (= 8 tens) (str "eighty" (parse-to-word (rem x 10)))
     (= 9 tens) (str "ninety" (parse-to-word (rem x 10)))
     :else ""))
  (= 1 x) "one"
  (= 2 x) "two"
  (= 3 x) "three"
  (= 4 x) "four"
  (= 5 x) "five"
  (= 6 x) "six"
  (= 7 x) "seven"
  (= 8 x) "eight"
  (= 9 x) "nine"
  (= 10 x) "ten"
  (= 11 x) "eleven"
  (= 12 x) "twelve"
  (= 13 x) "thirteen"
  (= 14 x) "fourteen"
  (= 15 x) "fifteen"
  (= 16 x) "sixteen"
  (= 17 x) "seventeen"
  (= 18 x) "eighteen"
  (= 19 x) "nineteen"
  :else ""))

(reduce (fn [x y] (+ x (count (seq y)))) 0 (map parse-to-word (range 1 1001)))
