(ns rps.core
  (:require [rps.views :as views]
            [rps.state :as state]))

(defn init []
  (println "Inicializando frontend...")
  (views/render-app)) 