(ns rps.views
  (:require [reagent.core :as reagent]
            [rps.state :as state]
            [rps.client :as client]))

(defn home-view []
  [:div.home
   [:h1 "Pedra, Papel e Tesoura"]
   [:button {:on-click #(state/set-current-view :game)}
    "Começar Jogo"]])

(defn game-view []
  [:div.game
   [:h2 "Faça sua jogada"]
   [:div.buttons
    [:button {:on-click #(client/play-move :rock)} "Pedra"]
    [:button {:on-click #(client/play-move :paper)} "Papel"]
    [:button {:on-click #(client/play-move :scissors)} "Tesoura"]]])

(defn stats-view []
  [:div.stats
   [:h2 "Estatísticas"]
   [:p (str "Vitórias: " (:wins @state/app-state))]
   [:p (str "Derrotas: " (:losses @state/app-state))]
   [:p (str "Empates: " (:ties @state/app-state))]
   [:p (str "Porcentagem de vitórias: " (:win-percentage @state/app-state) "%")]])

(defn render-app []
  (let [current-view (:current-view @state/app-state)]
    [:div.app
     (case current-view
       :home [home-view]
       :game [game-view]
       :stats [stats-view])])) 