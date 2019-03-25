(ns atbash-cipher
  "Create an implementation of the atbash cipher, an ancient encryption system
  created in the Middle East.

  The Atbash cipher is a simple substitution cipher that relies on transposing
  all the letters in the alphabet such that the resulting alphabet is
  backwards. The first letter is replaced with the last letter, the second
  with the second-last, and so on.

  See http://en.wikipedia.org/wiki/Atbash")

(def alphabet (map char (range (int \a) (inc (int \z)))))

(def numbers (map char (range (int \0) (inc (int \9)))))

(def cipher (zipmap (concat alphabet numbers) (concat (reverse alphabet) numbers)))

(defn encode [s]
  (let [lc (clojure.string/lower-case s)
        an (clojure.string/replace lc #"\W" "")]
    (->> an
         (map cipher)
         (partition-all 5)
         (map #(apply str %))
         (clojure.string/join \space))))
