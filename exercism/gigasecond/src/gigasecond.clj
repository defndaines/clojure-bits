(ns gigasecond
  (:import (java.time Period LocalDate)))

(def period (Period/ofDays (/ 1000000000 60 60 24)))

(defn from [year month day]
  (let [date (.addTo period (LocalDate/of year month day))]
    [(.getYear date)
     (.getValue (.getMonth date))
     (.getDayOfMonth date)]))