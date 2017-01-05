(ns roman-numerals)

;; Handle the decimal units, so values that reduce to 1-3 and 6-8.
(defn ^:private unit [modulus ch]
  (fn [n]
    (let [x (rem (/ n modulus) 5)]
      (when (> 4 x)
        (apply str (repeat x ch))))))

;; Handle the consolidation and semi cases, 4, 5, and 9.
(defn ^:private decimate [modulus pred s]
  (fn [n]
    (when (pred (rem (quot n (/ modulus 10)) 10))
      s)))

;; Assemble four functions associated with the same modulus.
;; The terms (I made up) are: deci, abut deci, semi-deci, abut semi.
(defn ^:private four-fn [modulus d ad sd as]
  [(unit modulus (first (str d)))
   (decimate modulus (partial = 9) ad)
   (decimate modulus (fn [n] (> 9 n 4)) sd)
   (decimate modulus (partial = 4) as)])

;; Assemble the rules in descending order to render correctly.
(def ^:private rules
  (concat
    (mapcat
      #(apply four-fn %)
      [[1000 "M" "CM" "D" "CD"]
       [ 100 "C" "XC" "L" "XL"]
       [  10 "X" "IX" "V" "IV"]])
    [(unit 1 \I)]))

(defn numerals [n]
  (apply str
         (reduce (fn [acc e] (concat acc (e n)))
                 '()
                 rules)))