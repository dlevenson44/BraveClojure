(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])

(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn symmetrize-body-parts
  "Expects a sequence of maps that have a :name and a :size"
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts
         final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts]
        (recur remaining
               (into final-body-parts
                     (set [part (matching-part part)])))))))
; Calling function below would create a right-bodypart for any left-bodypart that exists
(symmetrize-body-parts asym-hobbit-body-parts)

(defn better-symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
            (into final-body-parts (set [part (matching-part part)])))
          []
          asym-body-parts))
(better-symmetrize-body-parts asym-hobbit-body-parts)

(defn hit
  [asym-body-parts]
  (let [sym-parts (better-symmetrize-body-parts asym-body-parts)
        body-part-size-sum (reduce + (map :size sym-parts))
        target (rand body-part-size-sum)]
    (loop [[part & remaining] sym-parts
           accumulated-size (:size part)]
      (if (> accumulated-size target)
        part
        (recur remaining (+ accumulated-size (:size (first remaining))))))))
(hit asym-hobbit-body-parts)
; Use the str, vector, list, hash-map, and hash-set functions
; str function
(str "This is my awesome string!")
; vector of numbers
(vector 1 2 3 4 5 6)
; vector of strings and numbers
(vector (str "First string!") (str "Second string!") (str "Third string!") 4 5 6)
; list literal of numbers
`(1 2 3 4 5 6)
; lister lieral of strings and numbers
`("One" "Two" "Three" 4 5 6)
; Empty hash-map
{}
; Hash-map with data
{:first-name "Dan"
 :last-name  "McCoolGuy"}
; Nested hash-map
{:name {:first-name "dan" :last-name "mccoolguy"}}
; Hash-set function
(hash-set 1 1 2 2)
; Set function
(set [3 3 4 4 4])


; Write a function that takes a number and adds 100
(defn plus-hundred
  [x]
  (+ x 100))
;; (plus-hundred 100)
;; (plus-hundred 1000)

; Write a function that runs a separate function to generate a result
(defn dec-maker
  "This function decreases a value by X amount"
  [dec-by]
  #(- % dec-by))
(dec-maker 10)

 (def dec9
  "This function uses dec-by to decrease provided value by 9"
  (dec-maker 9))
(dec9 10)
(dec9 100)

(def dec5
  "this uses dec-by to decrease in increments of 5"
  (dec-maker 5))
(dec5 10)
(dec5 25)

