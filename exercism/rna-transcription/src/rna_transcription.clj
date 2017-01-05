(ns rna-transcription)

(def rna
  {\G \C
   \C \G
   \T \A
   \A \U}) 

(defn safe-tr [n]
  (if-let [x (get rna n)]
    x
    (assert false)))

(defn to-rna [s]
  (apply str (map safe-tr s)))