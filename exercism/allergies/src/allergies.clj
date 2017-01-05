(ns allergies)

(def allergens [:eggs :peanuts :shellfish :strawberries :tomatoes :chocolate :pollen :cats])

(defn allergic-to? [n a]
  (let [i (.indexOf allergens a)]
    (bit-test n i)))

(defn allergies [a]
  (reduce
    (fn [acc [index allergy]] (if (bit-test a index) (conj acc allergy) acc))
    []
    (map vector (iterate inc 0) allergens)))