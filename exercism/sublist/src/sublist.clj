(ns sublist)

(defn- sublist?
  "Given a `smaller` and `larger` list (it is up to the caller to determine
  this beforehand), determine if the smaller list is a sublist of the larger
  one."
  [smaller larger]
  (let [n (count smaller)]
    (if (> n (count larger))
      false
      (if (= smaller (take n larger))
        true
        (sublist? smaller (rest larger))))))

(defn classify [list1 list2]
  (cond
    (= list1 list2) :equal
    (sublist? list1 list2) :sublist
    (sublist? list2 list1) :superlist
    :else :unequal))
