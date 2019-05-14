(ns game-of-snake.state
  (:require
   [reagent.core :as reagent]))


;; Application state
(defonce state (reagent/atom {:time 0
                              :direction :left
                              :snake [[25 25]
                                      [25 26]
                                      [25 27]]
                              :delay 500}))

(defn update-state! [f & args]
  (swap! state f)
  (js/console.info @state))

