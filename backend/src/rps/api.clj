(ns rps.api
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.json :as json]
            [rps.game :as game]
            [rps.stats :as stats]))

(def game-state (atom {:history []}))

(defroutes app-routes
  (POST "/play" request
    (let [player-move (get-in request [:body :move])
          computer-move (rand-nth game/moves)
          result (game/play-round player-move computer-move)]
      (swap! game-state update :history conj result)
      {:status 200
       :body result}))
  
  (GET "/stats" []
    {:status 200
     :body (stats/calculate-stats (:history @game-state))})
  
  (route/not-found "Not Found"))

(def app
  (-> app-routes
      (json/wrap-json-body)
      (json/wrap-json-response))) 