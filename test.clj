(ns test
  (:require
   [clojure.set :as set]
   [clojure.string :as str]))

(defn create-account [id initial-balance]
  {:id id
   :balance initial-balance
   :transactions []})

(defn get-balance [account]
  (:balance account))

(defn update-balance [account amount]
  (-> account
      (update :balance + amount)
      (update :transactions conj {:amount amount :timestamp (System/currentTimeMillis)})))

(defn validate-transfer [from-account to-account amount]
  (and (>= (get-balance from-account) amount)
       (pos? amount)
       (not= (:id from-account) (:id to-account))))

(defn transfer [from-account to-account amount]
  (if (validate-transfer from-account to-account amount)
    [(update-balance from-account (- amount))
     (update-balance to-account amount)]
    [from-account to-account]))

(defn create-bank []
  (atom {}))

(defn add-account [bank account-id initial-balance]
  (swap! bank assoc account-id (create-account account-id initial-balance)))

(defn get-account [bank account-id]
  (get @bank account-id))

(defn process-transfer [bank from-id to-id amount]
  (swap! bank
         (fn [current-state]
           (let [from-account (get current-state from-id)
                 to-account (get current-state to-id)
                 [updated-from updated-to] (transfer from-account to-account amount)]
             (assoc current-state
                    from-id updated-from
                    to-id updated-to)))))

(def bank (create-bank))

(add-account bank "alice" 1000)
(add-account bank "bob" 500)
(process-transfer bank "alice" "bob" 300)

(println "Alice's balance:" (get-balance (get-account bank "alice")))
(println "Bob's balance:" (get-balance (get-account bank "bob")))
