(def VERSION "0.11.0-SNAPSHOT")

(defproject cider/cider-nrepl VERSION
  :description "nREPL middlewares for CIDER"
  :url "https://github.com/clojure-emacs/cider-nrepl"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/tools.nrepl "0.2.12"]
                 [org.tcrawley/dynapath "0.2.3"]
                 ^:source-dep [compliment "0.2.5"]
                 ^:source-dep [cljs-tooling "0.1.9"]
                 ^:source-dep [cljfmt "0.3.0"]
                 ^:source-dep [org.clojure/java.classpath "0.2.3"]
                 ^:source-dep [org.clojure/tools.namespace "0.2.11"]
                 ^:source-dep [org.clojure/tools.trace "0.7.9"]
                 ^:source-dep [org.clojure/tools.reader "0.10.0"]]
  :exclusions [org.clojure/clojure]
  :test-paths ["test/common"] ;; See `test-clj` and `test-cljs` profiles below.
  :plugins [[thomasa/mranderson "0.4.5"]]
  :filespecs [{:type :bytes :path "cider/cider-nrepl/project.clj" :bytes ~(slurp "project.clj")}]
  :profiles {:provided {:dependencies [[org.clojure/clojure "1.7.0"]]}

             :dev {:repl-options {:nrepl-middleware [cider.nrepl.middleware.apropos/wrap-apropos
                                                     cider.nrepl.middleware.classpath/wrap-classpath
                                                     cider.nrepl.middleware.complete/wrap-complete
                                                     cider.nrepl.middleware.debug/wrap-debug
                                                     cider.nrepl.middleware.format/wrap-format
                                                     cider.nrepl.middleware.info/wrap-info
                                                     cider.nrepl.middleware.inspect/wrap-inspect
                                                     cider.nrepl.middleware.macroexpand/wrap-macroexpand
                                                     cider.nrepl.middleware.ns/wrap-ns
                                                     cider.nrepl.middleware.out/wrap-out
                                                     cider.nrepl.middleware.pprint/wrap-pprint
                                                     cider.nrepl.middleware.refresh/wrap-refresh
                                                     cider.nrepl.middleware.resource/wrap-resource
                                                     cider.nrepl.middleware.stacktrace/wrap-stacktrace
                                                     cider.nrepl.middleware.test/wrap-test
                                                     cider.nrepl.middleware.trace/wrap-trace
                                                     cider.nrepl.middleware.track-state/wrap-tracker
                                                     cider.nrepl.middleware.undef/wrap-undef]}
                   :repositories [["snapshots"
                                   "https://oss.sonatype.org/content/repositories/snapshots"]]
                   :dependencies [[org.clojure/tools.nrepl "0.2.12"]]}

             :test {:resource-paths ["test/resources"]}
             :test-clj {:test-paths ["test/clj"]}
             :test-cljs {:test-paths ["test/cljs"]
                         :dependencies [[com.cemerick/piggieback "0.2.1"]
                                        [org.clojure/clojurescript "0.0-3211"]]}

             :coveralls {:plugins [[lein-cloverage "1.0.6"]
                                   [lein-shell "0.5.0"]]
                         :aliases {"coveralls" ["do" "cloverage" "--coveralls,"
                                                "shell" "curl" "-F"
                                                "json_file=@target/coverage/coveralls.json"
                                                "https://coveralls.io/api/v1/jobs"]}}

             :1.7 {:dependencies [^:replace [org.clojure/clojure "1.7.0"]
                                  [org.clojure/clojure "1.7.0" :classifier "sources"]
                                  [org.clojure/clojure "1.7.0" :classifier "javadoc"]]}

             :cljfmt {:plugins [[lein-cljfmt "0.3.0"]]
                      :cljfmt {:indents {as-> [[:inner 0]]}}}
             :eastwood {:plugins [[jonase/eastwood "0.2.2"]]
                        :eastwood {:config-files ["eastwood.clj"]}}})
