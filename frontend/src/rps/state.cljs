(ns rps.state
  (:require [reagent.core :as reagent]))

(def app-state
  (reagent/atom
   {:current-view :home
    :game-history []
    :stats {:wins 0 :losses 0 :ties 0 :win-percentage 0}}))

(defn update-stats [stats]
  (swap! app-state assoc :stats stats))

(defn add-to-history [result]
  (swap! app-state update :game-history conj result))

(defn set-current-view [view]
  (swap! app-state assoc :current-view view)) 