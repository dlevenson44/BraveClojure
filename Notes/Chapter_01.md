
###### Project 1: Leiningen
- Leiningen is used to build and manage projects... next steps will walk through creating, running, building Clojure project, as well as using REPL
1. Run `lein new app clojure-noob` to generate the project!
- project.clj is a config file that tells Leiningen what dependencies the project has, when functions should run
- Source code generally gets saved in the `src/project-name` folder, in this case `src/clojure-noob`
- `src/clojure-noob/core.clj` is where Clojure code is written
```
; this declares namespace
(ns clojure-noob.core
  (:gen-class))
```
```
; Entry point to program
(defn -main
  "I don't do a whole lot... yet."
  [& args]
    (println "hello world!"))
```
- `test` contains tests
- `resources` is where you store assets such as images
2. Run `lein run` from `clojure_noob` directory to run program-- will display whatever the println value is
3. Can run `lein uberjar` to create a file that anyone with Java can execute
- This command creates `target/uberjar/clojure-noob-0.1.0-SNAPSHOT-standalone.jar`
- Can execute with Java by running `java -jar target/uberjar/clojure-noob-0.1.0-SNAPSHOT-standalone.jar`
- If you make a change to a file, you need to recompile in order to rerun on Java
4. Run `lein repl` to start the repl-- used to experiment with
- Can run functions from `core.clj` file, ie running `(-main)` within the REPL
- Can also run (+ 1 2 3 4) => 10, and (first [1 2 3 4]) => 1
```
clojure-noob.core=> (do (println "no prompt here!")
               #_=> (+ 1 3))
no prompt here!
4
```