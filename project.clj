(defproject stiefel "0.1.0-SNAPSHOT"
  :dependencies [[compojure "1.1.5"]
                 [enfocus "2.0.0-SNAPSHOT"]
                 [org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-1552"]
                 [shoreleave "0.3.0"]
                 [shoreleave/shoreleave-browser "0.3.0"]
                 [shoreleave/shoreleave-pubsub "0.3.0"]
                 [shoreleave/shoreleave-remote "0.3.0"]
                 [shoreleave/shoreleave-remote-ring "0.3.0"]
                 [shoreleave/shoreleave-services "0.3.0"]
                 [webfui "0.2.1"]]
  :plugins [[lein-cljsbuild "0.3.0"]
            [lein-ring "0.8.5"]]
  :ring {:handler stiefel.backend/app}
  :source-paths ["src/clj"]
  :cljsbuild {:builds [{:source-paths ["src/cljs"]
                        :compiler {:output-to "resources/public/js/app.js"
                                   :optimizations :whitespace
                                   :pretty-print true}}]})

