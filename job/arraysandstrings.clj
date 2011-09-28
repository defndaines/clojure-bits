; 1.1 Determine if a string has unique characters.
(defn chars-unique?
 [s]
 (= (count (seq s)) (count (set s))))

; 1.2 Reverse a C-style string
;     ... problem is, how to create a C-style string in Clojure?
(defn c-reverse
 [s]
 (let [rev (reverse s)]
  (conj (seq (first rev)) (drop 1 rev))))
