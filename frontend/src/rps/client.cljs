(ns rps.client)

(def api-url "http://localhost:3000")

(defn play-move [move]
  (-> (js/fetch (str api-url "/play")
                #js {:method "POST"
                     :headers #js {"Content-Type" "application/json"}
                     :body (js/JSON.stringify #js {:move move})})
      (.then #(.json %))
      (.then #(js->clj % :keywordize-keys true))))

(defn get-stats []
  (-> (js/fetch (str api-url "/stats"))
      (.then #(.json %))
      (.then #(js->clj % :keywordize-keys true)))) 