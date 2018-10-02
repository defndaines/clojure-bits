(ns proverb)

(defn for-want [[object lost]]
  (str "For want of a " object " the " lost " was lost."))

(defn and-all [[a b c & _]]
  (str "And all for the want of a " c b " " a "."))

(defn gen-proverb
  [l]
  (let [what-started-it-all l]
    (clojure.string/join
      "\n"
      (conj
        (mapv
          for-want
          (partition 2 1 l))
        (and-all what-started-it-all)))))

(def objects ["nail", "shoe", "horse", "rider", "message", "battle", "kingdom"])

(def proverb (gen-proverb objects))
