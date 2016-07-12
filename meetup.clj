(ns meetup)
;; Spoiler Alert: This was for the exercism.io "meetup" problem.

(def days
  {:saturday 0
   :sunday 1
   :monday 2
   :tuesday 3
   :wednesday 4
   :thursday 5
   :friday 6})

(defn day-of-week
  "Use Zeller's congruence to get day of the week, Saturday = 0."
  [year month day]
  (let [m (+ (mod (+ month 9) 12) 3)
        Y (if (> month 2) year (dec year))]
    (mod (+ day
            (quot (* 13 (inc m)) 5)
            Y
            (quot Y 4)
            (- (quot Y 100))
            (quot Y 400))
         7)))

(defn leap? [year]
  (cond
    (< 0 (rem year 4)) false
    (zero? (rem year 400)) true
    (zero? (rem year 100)) false
    :else true))

(defn last-week-of-month [year month]
  (if (= 2 month)
    (if (leap? year) 23 22)
    (+ 24 (rem (+ month (quot month 8)) 2))))

(def period-start
  {:first 1
   :second 8
   :third 15
   :fourth 22
   :teenth 13})

(defn first-day [period year month]
  (get period-start period (last-week-of-month year month)))

(defn days-to [start-day goal]
  (rem (+ 7 (goal days) (- start-day)) 7))

(defn meetup
  [month year day period]
  (let [start (first-day period year month)
        start-day (day-of-week year month start)
        more-days (days-to start-day day)]
    [year month (+ start more-days)]))
