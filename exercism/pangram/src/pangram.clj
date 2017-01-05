(ns pangram)

(defn pangram? [s]
  false)
;; return 26 == phrase.toLowerCase(Locale.ENGLISH)
;;                    .replaceAll("\\P{Alpha}", "")
;;                    .chars()
;;                    .distinct()
;;                    .count();
