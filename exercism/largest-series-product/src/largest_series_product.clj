(ns largest-series-product
  "Given a string of digits, calculate the largest product for a contiguous
  substring of digits of length n.

  For example, for the input '1027839564', the largest product for a series of
  3 digits is 270 (9 * 5 * 6), and the largest product for a series of 5
  digits is 7560 (7 * 8 * 3 * 9 * 5).

  Note that these series are only required to occupy adjacent positions in the
  input; the digits need not be numerically consecutive.

  For the input '73167176531330624919225119674426574742355349194934', the
  largest product for a series of 6 digits is 23520.")

(defn ^:private max-product [n s]
  {:pre [(re-matches #"\p{Digit}+" s)]}
  (reduce
    (fn [acc e] (max acc (reduce * e)))
    0
    (partition n 1 (map #(Character/getNumericValue %) s))))

(defn largest-product [n s]
  {:pre [(not (neg? n))
         (<= n (count s))]}
  (if (zero? n)
    1
    (max-product n s)))
