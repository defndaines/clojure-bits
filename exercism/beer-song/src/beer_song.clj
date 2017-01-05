(ns beer-song)

(defn ^:private bottles [n]
  (case n
    1 "1 bottle"
    0 "no more bottles"
    -1 "99 bottles"
    (str n " bottles")))

(defn ^:private action [n]
  (case n
    0 "Go to the store and buy some more"
    1 "Take it down and pass it around"
    "Take one down and pass it around"))

(defn verse [n]
  (str (clojure.string/capitalize (bottles n)) " of beer on the wall, "
       (bottles n) " of beer.\n"
       (action n) ", "
       (bottles (dec n)) " of beer on the wall.\n"))

(defn sing
  ([n] (sing n 0))
  ([from to]
   (clojure.string/join \newline
                        (map verse (range from (dec to) -1)))))
