; The Little LISPer (Trade Edition, 1987)
; Translated to Clojure

; Where the text overwrites existing functions, I have changed the name.
; The first two functions below are necessary for much of the remaining code.
; The goal here is to stay as loyal to the style used in the LISPer even when more concise code would achieve the same aims, but in cases where a boolean is sought, false is returned instead of nil.

;Clojure does not define (atom?) or anything equivalent that I am aware of
(defn atom?
  "Return true if x is an atom"
  [x]
  (not (seq? x)))

;Clojure does not treat nil and () as equal
;This allows us to more robustly mimic the code of LISPer
(defn null?
  "Return true if x is nil or ()."
  [x]
  (or (nil? x) (= () x)))


