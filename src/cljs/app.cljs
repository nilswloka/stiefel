(ns stiefel.app
  (:require [stiefel.navigation :as nav])
  (:require-macros [enfocus.macros :as em]))

(defn ^:export run []
  (em/wait-for-load
   (nav/render-navigation! "Stiefel" [{:active true :url "#home" :text "Home" :view-name "home"}
                                      {:active false :url "#forms" :text "Forms" :view-name "forms"}])))