(ns clock)

(def day-in-min (* 60 24))

(defn clock [hh mm]
  (let [ms (rem (+ mm (* 60 hh)) day-in-min)]
    (if (neg? ms)
      (+ day-in-min ms)
      ms)))

(defn clock->string [c]
  (let [h (quot c 60)
        m (rem c 60)]
    (format "%02d:%02d" h m)))

(defn add-time [c ms]
  (let [sum (+ c ms)]
    (clock (quot sum 60) (rem sum 60))))
