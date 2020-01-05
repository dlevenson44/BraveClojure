### Pullling It All Together
- In this exercise we need to smack hobbits.  To hit a hobbit, we first model its body parts
- Each body part will include its relative size to indicate how likely it is that part will be hit
- To avoid repetition the hobbit model will include only entries for the left foot, left ear, and so on
- We need a function to symmetrize the model, creating right foot, right ear, and so on (if left in name, create a duplicate starting with right)
- Create a function that iterates over the body parts and randomly chooses the one hit
- This activity covers `let` expressions, loops, and regex

- For hobbit model would look like below example as a vector of maps
```
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
```

- Below functionality would create a right-bodypart for every left-bodypart that exists in the asym-hobbit-body-parts dataset
```
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
```
- `let` block starting on line 45
-- Creates new scope, where we associate `part` with the first element of the `remaining-asym-parts`... `remaining` associated with the rest of the elements in the `remaining-asym-parts` vector
- `into final-body-parts` on line 47
-- tells Clojure to use `set` function to create a set consisting of part and its matching part
-- Then use the `into` function to add elements of the set to the vector `final-body-parts`
-- Create a set here to ensure unique elements are added to final-body-parts because `part` and `(matching-part part)` are sometimes the same value
-- In a simplified example of this, we could do
```
(into [] (set [:a :a]))
; => [:a]
```
-- This returns the set `#{:a}` since sets can have unique values, and then `into [] #{:a}` returns the vector `[:a]`
-- So in our "real" example, we create a set from the `part` and the `(matching-part part)` that generates `#{part matching-part}` or `#{part}` and returns the vector `[part matching-part]` or `[part]`

- `loop` block starting on line 41
- Gives us a way to do recursion in Clojure
- We initiate the loop by binding `remaining-asym-parts` to the `asym-body-parts` value, and it sets final-body-parts to `[]`
- If `remaining-asym-parts` is empty, then we create the new let scope
- We then recur through the remaining body parts, create a set to put the part into the `final-body-parts` vector

- `regex` block starts on line 35
- `regex` is a tool to perform pattern matching on text
- Literal notation on regex is to place the expression in quotes after a hash-mark
- In function we match strings with `left-` to `#"^left-`, and replace them with `right-` (replace comes from `clojure.string/replace`)
- The `^` is how regex signals that it will match the text `left` only if it's at the begining of the string, preventing `cleft-chin` from matching
- This can be tested with `re-find`, which checks whether a string matches the pattern described by a regex, returning the matched text or `nil` if no match
```
(re-find #"^left-" "left-eye")
; => "left-"

(re-find #"^left-" "cleft-chin")
; => nil

(re-find #"^left-" "wongleblart")
; => nil
```
- Below we can see the `matchingpart` function in action at a more micro level
```
(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})
(matching-part {:name "left-eye" :size 1})
; => {:name "right-eye" :size 1}

(matching-part {:name "head" :size 3})
; => {:name "head" :size 3}
```

- `let` binds names to values... introduces local names for values, helping to simplify code
- The value of a `let` form is the last form in its body that is evaluated
- `let` forms follow the destructuring rules discussed earlier
- In below example, `[pongo & dalmations]` destructed `dalmation-list`, binding "Pongo" to the name `pongo` and the rest of the list of dalmations to `dalmations`
- Vector `[pongo dalmations]` is the last expression in let, so that's the let value form
```
(let [x 3]
  x)
; => 3

; bind dalmations to (take 2 dalmation-list)
(def dalmation-list
  ["Pongo" "Perdita" "Puppy 1" "Puppy 2"])
(let [dalmations (take 2 dalmation-list)]
  dalmations)
; => ("Pongo" "Perdita")

; let also introduces a new scope
(def x 0)
(let [x 1] x)
; => 1

(def x 0)
(let [x (inc x)] x)
; => 1
```
- `let` forms are used for two main reasons
1. They provide clarity by letting you name things
2. They let you evaluate an expression once and reuse the result
- This is especially important when you need to reuse the result of an expensive network API call

- `loop` gives us a way to do recursion in Clojure, similar to a for loop
- In below example, we increment the iteration if the iteration is not greater than 3
- If the iteration is greater than 3 then we println "goodbye!" and end the loop
```
; first line beings loop, introduces binding with an initial value
(loop [iteration 0]
  (println (str "Iteration " iteration))
  ; Checks value of iteration, if greater than 3 say goodbye!
  (if (> iteration 3)
    (println "Goodbye!")
    ; otherwise, recur
    (recur (inc iteration))))
```
- Above example has better performance by using `loop`, but you could also write a recrusive function with the below as well
```
(defn recursive-printer
  ([]
   (recursive-printer 0))
  ([iteration]
   (println iteration)
   (if (> iteration 3)
     (println "Goodbye!")
     (recursive-printer (inc iteration)))))
(recursive-printer)
```