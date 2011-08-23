; How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?

(defn leap-year?
 [year]
 (if (zero? (rem year 100)) (not (zero? (rem year 400)))
  (zero? (rem year 4))))

; Known Sunday: 19500101
; Known Monday: 19000101
