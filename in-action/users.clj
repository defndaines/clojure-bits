(def users [
 {:username "kyle"
  :balance 175.00
  :member-since "2009-04-16"}
 {:username "zak"
  :balance 12.95
  :member-since "2009-02-01"}
 {:username "rob"
  :balance 98.50
  :member-since "2009-03-30"}
])

(defn username [user]
 (user :username))

(defn balance [user]
 (user :balance))

(defn sorter-using [ordering-fn]
 (fn [users]
  (sort-by ordering-fn users)))

(def poorest-first (sorter-using balance))

(def alphabetically (sorter-using username))
