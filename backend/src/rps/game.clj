(ns rps.game)

(def moves [:rock :paper :scissors])

(defn beats? [move1 move2]
  (cond
    (= move1 move2) :tie
    (and (= move1 :rock) (= move2 :scissors)) :win
    (and (= move1 :paper) (= move2 :rock)) :win
    (and (= move1 :scissors) (= move2 :paper)) :win
    :else :lose))

(defn play-round [player-move computer-move]
  (let [result (beats? player-move computer-move)]
    {:player-move player-move
     :computer-move computer-move
     :result result})) 