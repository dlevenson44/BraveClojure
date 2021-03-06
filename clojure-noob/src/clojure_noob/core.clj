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

(defn too-enthusiastic
  "Return a cheer that might be a bit too enthusiastic"
  [name]
  (str "OH. MY. GOD! " name "YOU ARE THE MOST DEFINITELY LIKE THE BEST "
       "MAN SLASH WOMAN EVER I LOVE YOU AND WE SHOULD RUN AWAY SOMEWHERE"))
(too-enthusiastic "Dan")
; Returns above console statement with "Dan" passed as name argument

(defn codeger-communication
  [whippersnapper]
  (str "Get off my lawn, " whippersnapper "!!!"))
(defn codeger
  [& whippersnappers]
  (map codeger-communication whippersnappers))
(codeger-communication "Dan")
(codeger "Dan" "Danny" "Daniel" "Danielsito")

(defn favorite-things
  [name & things]
  (str "Hi, " name ", here are my favorite things: "
       (clojure.string/join ", " things)))
(favorite-things "Dan" "food" "Eating" "cooking")

(defn my-first
  [[first-thing]]
  first-thing)
(my-first ["oven" "bike" "war-axe"])

(defn chooser
  [[first-choice second-choice & unimportant-choices]]
  (println (str "Your first choice is: " first-choice))
  (println (str "Your second choice is: " second-choice))
  (println (str "We're ignoring the rest of your choices. "
                "Here they are in case you need to cry over them: "
                (clojure.string/join ", " unimportant-choices))))
(chooser ["Marmalade", "Handsome Jack", "Pigpen", "Aquaman"])

(defn announce-treasure-location
  [{lat :lat lng :lng}]
  (println (str "Treatsure lat: " lat))
  (println (str "Treasure lng: " lng)))
(announce-treasure-location  {:lat 28.22 :lng 81.33})

(defn announce-treasure-location
  [{:keys [lat lng]}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))
(announce-treasure-location {:lat 33.22 :lng 22.11})

(defn number-comment
  [x]
  (if (> x 6)
    "Oh wow, that number is larger than 6!"
    "That number is smaller than 6!"))
; Returns first condition "Oh wow, that number is larger than 6!"
(number-comment 7)

; Returns second condition "That number is smaller than 6!"
(number-comment 5)

(map (fn [name] (str "Hi, " name))
     ["Darth Vader" "Mr. Magoo"])
; => ("Hi, Darth Vader" "Hi, Mr. Magoo")

((fn [x] (* x 3)) 8)
; =>  24

(#(* % 3) 9)

(map #(str "Hi, " %)
     ["Darth Vader" "Mr. Magoo"])

(#(str %1 " and " %2 " are your two arguments.") "cornbread" "grits")

(#(identity %&) 1 "blarg" :yip)

(defn inc-maker
  "Create a custom incrementor"
  [inc-by]
  #(+ % inc-by))
(def inc3 (inc-maker 3))
(inc3 7)
; => 10
(inc3 14)
; => 17

(let [x 3]
  x)
; => 3

(def dalmation-list
  ["Pongo" "Perdita" "Puppy 1" "Puppy 2"])
(let [dalmations (take 2 dalmation-list)]
  dalmations)
; => ("Pongo" "Perdita")

(loop [iteration 0]
  (println (str "Iteration " iteration))
  (if (> iteration 3)
    (println "Goodbye!")
    (recur (inc iteration))))

(defn recursive-printer
  ([]
   (recursive-printer 0))
  ([iteration]
   (println iteration)
   (if (> iteration 3)
     (println "Goodbye!")
     (recursive-printer (inc iteration)))))
(recursive-printer)

(reduce + 15 [1 2 3 4])
; => 15 + 10 = 25
