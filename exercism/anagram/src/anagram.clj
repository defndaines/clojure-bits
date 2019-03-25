(ns anagram
  "Given a word and a list of possible anagrams, select the correct sublist.

  Given 'listen' and a list of candidates like 'enlists' 'google' 'inlets'
  'banana' the program should return a list containing 'inlets'."
  (:require [clojure.string :as str]))

(defn ^:private anagram? [x y]
  (let [lx (str/lower-case x)
        ly (str/lower-case y)]
    (and
      (not= lx ly)
      (= (sort lx) (sort ly)))))

(defn anagrams-for [s ws]
  (filter (partial anagram? s) ws))
