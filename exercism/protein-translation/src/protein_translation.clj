(ns protein-translation)

(def codon->amino-acid
  {"AUG" "Methionine"
   "UAA" "STOP"
   "UAC" "Tyrosine"
   "UAG" "STOP"
   "UAU" "Tyrosine"
   "UCA" "Serine"
   "UCC" "Serine"
   "UCG" "Serine"
   "UCU" "Serine"
   "UGA" "STOP"
   "UGC" "Cysteine"
   "UGG" "Tryptophan"
   "UGU" "Cysteine"
   "UUA" "Leucine"
   "UUC" "Phenylalanine"
   "UUG" "Leucine"
   "UUU" "Phenylalanine"})

(defn translate-codon [codon]
  (get codon->amino-acid codon codon))

(defn translate-rna [rna]
  (->> rna
       (partition 3)
       (map (partial apply str))
       (map translate-codon)
       (take-while #(not (= "STOP" %)))))
