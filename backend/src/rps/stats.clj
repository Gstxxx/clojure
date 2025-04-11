(ns rps.stats)

(defn calculate-stats [history]
  (let [total (count history)
        wins (count (filter #(= :win (:result %)) history))
        losses (count (filter #(= :lose (:result %)) history))
        ties (count (filter #(= :tie (:result %)) history))]
    {:total total
     :wins wins
     :losses losses
     :ties ties
     :win-percentage (if (zero? total) 0 (float (/ wins total)))})) 