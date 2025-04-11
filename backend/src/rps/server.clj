(ns rps.server
  (:require [ring.adapter.jetty :as jetty]
            [rps.api :as api]))

(defn start-server [port]
  (jetty/run-jetty api/app {:port port :join? false}))

(defn -main [& args]
  (let [port (or (some-> args first Integer/parseInt) 3000)]
    (start-server port)
    (println (str "Servidor rodando na porta " port)))) 