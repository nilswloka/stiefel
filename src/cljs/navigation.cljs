(ns stiefel.navigation
  (:require [enfocus.core :as ef]
            [enfocus.events :as events]
            [shoreleave.pubsubs.protocols :as pubsub]
            [stiefel.pubsub :as sps])
  (:require-macros [enfocus.macros :as em]))

(def navigation-state (atom nil))

(sps/topic navigation-state)

(defn highlight-navigation-item [{:keys [old new]}]
  (do
    (when (not (nil? old))
      (ef/at js/document
             [(str "#nav-item-" old)] (ef/remove-class "active")))
    (ef/at js/document
           [(str "#nav-item-" new)] (ef/add-class "active"))))

(sps/subscribe navigation-state highlight-navigation-item)

(em/deftemplate navigation-bar "templates/navigation.html"
  [name]
  ["a.brand"] (ef/content name))

(em/defsnippet navigation-item "templates/navigation.html" ["ul.nav > *:first-child"]
  [{:keys [active url text view-name]}]
  ["li"] (ef/do-> (ef/add-class (if active "active" "")) (ef/set-attr :id (str "nav-item-" view-name)))
  ["li a"] (ef/do-> (ef/set-attr :href url) (ef/content text) (events/listen :click #(reset! navigation-state view-name))))

(defn render-navigation! [name items]
  (let [active-item (first (filter #(:active %) items))
        active-view-name (:view-name active-item)]
    (reset! navigation-state active-view-name)
    (ef/at js/document
           ["#navigation"] (ef/content (navigation-bar name))
           ["ul.nav"] (ef/content (map navigation-item items)))))