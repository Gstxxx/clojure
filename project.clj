(defproject rps-game "0.1.0-SNAPSHOT"
  :description "Jogo de Pedra, Papel e Tesoura"
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [org.clojure/clojurescript "1.11.60"]
                 [ring "1.9.6"]
                 [compojure "1.7.0"]
                 [hiccup "1.0.5"]
                 [ring/ring-defaults "0.4.0"]
                 [ring/ring-json "0.5.1"]
                 [cheshire "5.11.0"]
                 [reagent "1.2.0"]
                 [cljsjs/react "17.0.2-0"]
                 [cljsjs/react-dom "17.0.2-0"]]
  :plugins [[lein-cljsbuild "1.1.8"]]
  :source-paths ["backend/src"]
  :test-paths ["backend/test"]
  :resource-paths ["frontend/resources"]
  :main rps.server
  :cljsbuild {:builds [{:source-paths ["frontend/src"]
                       :compiler {:output-to "frontend/resources/public/js/main.js"
                                :optimizations :advanced
                                :pretty-print false
                                :externs ["react/externs/react.js"]}}]}) 