(ns challenge
  (:require [clj-http.client :as http]
            [clojure.data.json :as json]))

;; Add actual URL here for the code to run live.
(def base-url "http://elided")


;; Have to provide a session ID in the header, but it expires after 10 uses.
(def session (atom {:id nil :used 0}))

(defn get-session []
  (if (or (nil? (:id @session))
          (<= 9 (:used @session)))
    (let [id (:body (http/get (str base-url "/get-session")))]
      (swap! session #(assoc % :id id :used 0)))
    (swap! session #(assoc % :used (inc (:used %)))))
  (:id @session))


;; The challenge explores a tree of nodes.
(def nodes (atom []))  ;; Keep in a vector since order matters.

(defn get-node [id]
  (json/read-str (:body (http/get (str base-url id)
                                  {:headers {:Session (get-session)}}))
                 :key-fn keyword))

(defn add-node! [obj]
  (swap! nodes conj obj))


;; Visit all the nodes, appending to @nodes.
(defn visit-all! [nodes]
  (loop [queue (into clojure.lang.PersistentQueue/EMPTY nodes)
         sub-nodes []]
    (if (seq queue)
      (let [id (peek queue)
            node (get-node id)]
        (add-node! node)
        (if-let [more-nodes (:next node)]
          ;; Next nodes could be an array or a single item.
          (if (vector? more-nodes)
            (recur (pop queue) (into sub-nodes more-nodes))
            (recur (pop queue) (conj sub-nodes more-nodes)))
          (recur (pop queue) sub-nodes)))
      sub-nodes)))


;; Descend each level, starting with "start".
(loop [nodes ["start"]]
  (when (seq nodes)
    (recur (visit-all! nodes))))


;; The secret message is derived from appending all the secret codes together.
(def message (apply str (map #(:secret %) @nodes)))
