(ns stiefel.navigation
  (:require [enfocus.core :as ef]
            [enfocus.events :as events]
            [stiefel.pubsub :as pubsub])
  (:require-macros [enfocus.macros :as em]))

(defn navigate-to! [view-name]
  (fn [event] (js/alert (str event))))

(em/deftemplate navigation-bar "templates/navigation.html"
  [name]
  ["a.brand"] (ef/content name))

(em/defsnippet navigation-item "templates/navigation.html" ["ul.nav > *:first-child"]
  [{:keys [active url text view-name]}]
  ["li"] (ef/do-> (ef/add-class (if active "active" "")) (ef/set-attr :id view-name))
  ["li a"] (ef/do-> (ef/set-attr :href url) (ef/content text) (events/listen :click (navigate-to! view-name))))

(defn render-navigation! [name items]
  (ef/at js/document
         ["#navigation"] (ef/content (navigation-bar name))
         ["ul.nav"] (ef/content (map navigation-item items))))


