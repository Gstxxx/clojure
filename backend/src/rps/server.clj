(ns rps.server
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.content-type :refer [wrap-content-type]]
            [rps.api :as api]))

(def app
  (-> api/app
      (wrap-resource "public")
      (wrap-content-type)
      (wrap-defaults site-defaults)))

(defn start-server [port]
  (jetty/run-jetty app {:port port :join? false}))

(defn -main [& args]
  (let [port (or (some-> args first Integer/parseInt) 3000)]
    (start-server port)
    (println (str "Servidor rodando na porta " port))))