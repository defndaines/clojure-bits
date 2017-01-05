(ns bob)

(defn shouting? [q]
  (let [{upper true lower false} (->> q
                                      (filter #(Character/isAlphabetic (int %)))
                                      (group-by #(Character/isUpperCase %)))]
    (> (count upper) (count lower))))

(defn response-for [q]
  (cond
    (empty? (clojure.string/trim q)) "Fine. Be that way!"
    (shouting? q) "Whoa, chill out!"
    (.endsWith q "?") "Sure."
    :else "Whatever."))