(ns anagram
  (:require [clojure.string :as str]))

(defn ^:private anagram? [x y]
  (let [lx (str/lower-case x)
        ly (str/lower-case y)]
    (and
      (not= lx ly)
      (= (sort lx) (sort ly)))))

(defn anagrams-for [s ws]
  (filter (partial anagram? s) ws))
