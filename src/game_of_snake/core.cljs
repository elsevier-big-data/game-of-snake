(ns ^:figwheel-hooks game-of-snake.core
  (:require [game-of-snake.incubator :as incubator]
            [goog.dom :as gdom]
            [reagent.core :as reagent]
            [game-of-snake.state :refer [update-state!]]))

;; Application view
;; --------------------------------------------------

(defn- app-view
  "The root view of the application."
  []
  [incubator/view {}])

;; Entry point
;; --------------------------------------------------

(defn- get-app-element []
  (gdom/getElement "app"))

(defn- mount [el]
  (reagent/render-component [app-view] el))

(defn mount-app-element
  "Conditionally load the application if an 'app' element is present,
  configuring hooks appropriately depending on whether this is a
  hot-reload or a full reload."
  [is-reload]
  (when-let [el (get-app-element)]
    (do
      (-> js/document
          (.addEventListener "keydown" (fn [e] (case (.-keyCode e)
                                                 37 (update-state! #(assoc % :direction :left))
                                                 38 (update-state! #(assoc % :direction :up))
                                                 39 (update-state! #(assoc % :direction :right))
                                                 40 (update-state! #(assoc % :direction :down))))))
      (mount el))))

(defn ^:after-load on-reload
  "Hook called on a hot-reload."
  []
  (mount-app-element true)
  ;; Optionally touch your state to force rerendering depending on your application
  ;; (swap! state update-in [:__figwheel_counter] inc)
)

(mount-app-element false)
