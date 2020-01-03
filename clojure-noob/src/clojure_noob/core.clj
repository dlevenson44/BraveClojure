(ns clojure-noob.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Dan is learning Clojure!"))

(defn train
  []
  (println "I'm a train... I mean, Choo Choo!"))

; whitespace examples
(+ 1 2 3) ; => 6
(str "It was the panda " "in the library " "with a dust buster")
; => "It was the panda in the library with a dust buster"

(if true
  "By Zeus's hammer!"
  "By Aquaman's trident!")
; => "By Zeus's hammer!"

(if false
  "By Zeus's hammer!"
  "By Aquaman's trident!")
; => "By Aquaman's trident!"

(if false
  "By Odin's Elbow!")
; => nil

(if true
  (do (println "Success!")
      "By Zeus's hammer!")
  (do (println "Failure!")
      "By Aquaman's Trident!"))
; REPL prints Success!
; Conditional returns "By Zeus's hammer!"

(if true
  (do (println "Success!")
      "By Zeus's hammer!")
  (do (println "Failure!")
      "By Aquaman's trident!"))
; =>  "Success!" in REPL, and "By Zeus's hammer! is the returned value

(if "bears eat beets"
  "bears beets Battlestar Galactica")
; => "bears beets Battlestar Galactica"

(if nil
  "This won't print because the result is nil which is falsey"
  "nil is falsey so this is being printed instead")
; => "nil is falsey so this is being printed instead"

(= 1 1)
; => true

(= nil nil)
; => true
(= 1 2)
; => false

(or false nil :large_I_mean_venti :why_cant_I_just_say_large)
; => :large_I_mean_venti

(or (= 0 1) (= "yes" "no"))
; => false

(or nil)
; => nil

(def failed-protagonist-names
  ["Larry Potter" "Doreen the Explorer" "The Incredible Bulk"])
; => ["Larry Potter" "Doreen the Explorer" "The Incredible Bulk"]

(defn error-message
  [severity]
  (str "OH GOD! IT'S A DISASTER! WE'RE "
       (if (= severity :mild)
         "MILDLY INCONVENIENCED!"
         "DOOOOOOOOOOMED!!!!!")))
; => (error-message :mild) prints first message
; => (error-message :severe) prints second message
; anything other than :mild prints second message
