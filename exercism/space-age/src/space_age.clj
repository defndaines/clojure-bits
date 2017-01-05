(ns space-age)

(def periods {"on-mercury" 0.2408467
              "on-venus" 0.61519726
              "on-earth" 1.0
              "on-mars" 1.8808158
              "on-jupiter" 11.862615
              "on-saturn" 29.447498
              "on-uranus" 84.016846
              "on-neptune" 164.79132})

(defn seconds->years [sec]
  (/ sec 31557600))

(doseq [[planet period] periods]
  (intern *ns*
          (symbol planet)
          (fn [seconds]
            (seconds->years (/ seconds period)))))