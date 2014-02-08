(def tk-version "0.3.2")

(defproject hello "0.1.0-SNAPSHOT"
  :description "Trapperkeeper hello world."
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.6"]
                 [puppetlabs/trapperkeeper-webserver-jetty7 "0.3.2"]
                  ]
  :plugins [[lein-ring "0.8.10"]]
  :ring {:handler hello.handler/app}
  :profiles {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}}

  :main puppetlabs.trapperkeeper.main
  )
