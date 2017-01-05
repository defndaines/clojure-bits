(ns bank-account)

(defn- non-negative?
  [{balance :balance}]
  (>= balance 0))

(defn open-account []
  (agent {:status :open
          :balance 0}
         :validator non-negative?))

(defn get-balance [acct]
  (let [{status :status balance :balance} @acct]
    (if (= :open status)
      balance)))

(defn update-balance [acct amt]
  (send acct update-in [:balance] + amt)
  ; Force tests to wait for balance to take effect.
  (await-for 10 acct))

(defn close-account [acct]
  (send acct assoc-in [:status] :closed))