(defproject rps-game "0.1.0-SNAPSHOT"
  :description "Jogo de Pedra, Papel e Tesoura"
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [org.clojure/clojurescript "1.11.60"]
                 [ring "1.9.6"]
                 [compojure "1.7.0"]
                 [hiccup "1.0.5"]]
  :plugins [[lein-cljsbuild "1.1.8"]]
  :source-paths ["backend/src"]
  :test-paths ["backend/test"]
  :resource-paths ["frontend/resources"]
  :cljsbuild {:builds [{:source-paths ["frontend/src"]
                       :compiler {:output-to "frontend/resources/public/js/main.js"
                                :optimizations :advanced
                                :pretty-print false}}]}) 