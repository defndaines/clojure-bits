(ns robot-simulator)

(defn robot [coordinates bearing]
  {:coordinates coordinates
   :bearing bearing})

(def ^:private bearings [:north :east :south :west])

(defn turn-right [bearing]
  (get bearings
       (rem (inc (.indexOf bearings bearing)) 4)))

(defn ^:private right [{bearing :bearing :as bot}]
  (assoc-in bot [:bearing] (turn-right bearing)))

(defn turn-left [bearing]
  (get bearings
       (rem (+ 3 (.indexOf bearings bearing)) 4)))

(defn ^:private left [{bearing :bearing :as bot}]
  (assoc-in bot [:bearing] (turn-left bearing)))

(defn ^:private advance [{bearing :bearing :as bot}]
  (case bearing
    :north (update-in bot [:coordinates :y] inc)
    :east (update-in bot [:coordinates :x] inc)
    :south (update-in bot [:coordinates :y] dec)
    :west (update-in bot [:coordinates :x] dec)))

(def ^:private instructions
  {\L left
   \R right
   \A advance})

(defn simulate [inst bot]
  (reduce
    (fn [acc e] ((get instructions e) acc))
    bot
    inst))