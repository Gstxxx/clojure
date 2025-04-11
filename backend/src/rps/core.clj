(ns rps.core
  (:require [rps.game :as game]
            [rps.stats :as stats]
            [rps.api :as api]))

(defn init []
  (println "Inicializando o jogo Pedra, Papel e Tesoura...")) 