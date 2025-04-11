(ns rps.client
  (:require [cljs-http.client :as http]))

(def api-url "http://localhost:3000")

(defn play-move [move]
  (http/post (str api-url "/play")
             {:json-params {:move move}}))

(defn get-stats []
  (http/get (str api-url "/stats"))) 