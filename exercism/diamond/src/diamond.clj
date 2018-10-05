(ns diamond)

(def a-pos (int \A))

(defn gen-A-row [padding]
  (apply
    str
    (concat
      (repeat padding \space)
      "A"
      (repeat padding \space))))

(defn row-for [padding char-as-int]
  (if (= char-as-int a-pos)
    (gen-A-row padding)
    (let [before (- padding (- char-as-int a-pos))
          after (- char-as-int a-pos 1)]
      (apply str
             (concat
               (repeat before \space)
               (list (char char-as-int))
               (repeat after \space)
               " "
               (repeat after \space)
               (list (char char-as-int))
               (repeat before \space))))))

(defn diamond [ch]
  (let [char-as-int (int ch)
        padding (- char-as-int a-pos)]
    (mapv
      (partial row-for padding)
      (concat
        (range a-pos char-as-int)
        (range char-as-int (dec a-pos) -1)))))
