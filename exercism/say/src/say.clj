(ns say)

(def words
  {1 "one" 2 "two" 3 "three" 4 "four" 5 "five" 6 "six" 7 "seven" 9 "nine"
   14 "fourteen"})

(def tys {2 "twenty" 3 "thirty" 4 "forty" 5 "fifty" 8 "eighty"})

(defn- under-a-thousand [n]
  (let [hundreds (quot n 100)
        tens (quot (rem n 100) 10)
        ones (rem n 10)]
    [(when (< 0 hundreds)
       [(words hundreds) "hundred"])
     (when (< 1 tens)
       (tys tens))
     (cond
       (= 1 tens) (words (+ 10 ones))
       (< 0 ones) (words ones))]))

(defn- lump [n unit]
  (when (< 0 n)
    [(under-a-thousand n) unit]))

(defn number [n]
  (cond
    (< 0 n 1000000000000)
    (clojure.string/replace
      (clojure.string/join
        " "
        (filter
          seq
          (flatten [(lump (quot n 1000000000) "billion")
                    (lump (rem (quot n 1000000) 1000) "million")
                    (lump (rem (quot n 1000) 1000) "thousand")
                    (under-a-thousand (rem n 1000))])))
      "y " "y-")

    (zero? n) "zero"
    :else (throw (IllegalArgumentException.))))
