(ns atbash-cipher)

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