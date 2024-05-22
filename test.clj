(ns test
  "My app"
  (:require
   [clojure.set :as set]))

(defn testFunc
  "choose one from map"
  []
  (println (set/intersection #{:r :b :w} #{:w :p :y}) 
           (set/difference #{:r :b :w} #{:w :p :y}) 
           (set/union #{:r :b :w} #{:w :p :y})))

(testFunc)
