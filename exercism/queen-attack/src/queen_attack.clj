(ns queen-attack)

(defn board-string [m] 
  :tbd)

(defn can-attack
  "Determine if two pieces are vertically, horizontally, or diagonally aligned."
  [{[wx wy] :w [bx by] :b}]
  (or
    (= wx bx)
    (= wy by)
    (= (Math/abs (- wx bx)) (Math/abs (- wy by)))))
