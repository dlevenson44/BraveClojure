### Functions
###### Calling Functions
- Function Call is just another term for an operation where the operator is a function/function expression
-- function expression: an expression that returns a function, ie `(or + i) :=> #<core$_PLUS_ clojure.core_PLUS_@76dace31>`
-- Returns a string represtation of a function
- Some examples of functions that we've called so far include:
```
(+ 1 2 3 4)
(* 1 2 3 4)
(first [1 2 3 4])
```
- In below example, `6` is retruned because the `or` condition finds the `+` function to be the first truthy
```
((or + -) 1 2 3)
=> 1 + 2 + 3 = 6
```
- Here, we take the first falsey/last truthy since we're calling `and` instead of `or`
- both `(= 1 1)` and `+` are truthy, but `+` comes second so that is returned from the function
```
((and (= 1 1) +) 1 2 3)
=> 6
```
- Finally, we use the `+` operator here because it is the first element found in the vector
```
((first [+ 0]) 1 2 3)
=> 6
```
- Numbers and strings are functions, so below would be some examples of invalid function calls
- Triggers the REPL error `ClassCastException java.lang.String cannot be cast to clojure.lang.IFnuser`
-- `<x> cannot be cast to clojure.lang.IFn` means you're trying to use something as a function when it isn't a function
```
(1 2 3 4)
("test" 1 2 3)
```
- Functions can take any expression as arguments, including other functions (JUST LIKE JS&RUBY!)
- Higher-Order Functions: functions that can either take a function as an argument or return a function
- Languages that support higher-order functions are said to support `first-class functions`
-- They say this because you can treat functions as values, the same way you treat other data types such as numbers, strings, and vectors
- Clojure functions can be created so they generalize over processes, such as `map`
- `map` is a function that creates a new list by applying another function to each member of a fcollection
- This is an example of higher-order function: `(map inc [0 1 2 3]) => (1 2 3 4)`
- A non-HOF would be `(inc 1.1) => 2.1`
- Clojure evaluates all function arguments recursively before passing them to the function
- For example:
```
(+ (inc 199) (/ 100 (- 7 2)))
(+ 200 (/100 (- 7 2))) => evaluated (inc 199) => 1 + 199
(+ 200 (/ 100 5)) => evaluate (- 7 2)
(+ 200 20) => evaluated (/100 5)
220 => final evaluation
```

###### Special Forms, Function & Macro Calls
- Function calls are expressions-- we've discussed function calls, the other two are macro calls and special forms
- Special forms include definitions and `if` expressions
- Special forms don't always evaluate all their operands, unlike function calls
-- You also can't use special forms as arguments to functions
-- Special forms implement core Clojure functionality that can't be implemented with functions
-- There're only a handful of special forms
- Macro Calls are similar to special forms in that they evaluate operands differently than function calls and they can't be passed as arguments to functions

- Function definitions are composed of 5 key parts
1. `defn`
2. Function name
3. A docstring describing the function (optional)
4. PArameters listed in brackets
5. Function body
- An example of a function definition and a sample call of the function
```
(defn too-enthusiastic => parts one and two
  "Return a cheer that might be a bit too enthusiastic" => part 3
  [name] => part 4
  (str "OH. MY. GOD! " name "YOU ARE THE MOST DEFINITELY LIKE THE BEST "
       "MAN SLASH WOMAN EVER I LOVE YOU AND WE SHOULD RUN AWAY SOMEWHERE")) => part 5
(too-enthusiastic "Dan")
```

- Docstring is a way to describe/document code
- Can view docstring for a function in the REPL with `(doc fn-name)`, for example, `(doc map)`
- Clojure functions can be defined with zero or more parameters
- The values you pass to functions are called orguments (duh)
- Arity: the number of a parameters in a function
- Some sample functions below with different arity values
```
(defn no-params
  []
  "I take no parameters!")
(defn one-param
  [x]
  (str "I take one parameter: " x))
(defn two-params
  [x y]
  (str "Two parameters! That's nothing! Pah! I will smoosh them "
  "together to spite you! " x y))
```
- Arity Overloading: you can define a function so a different function body will run depending on the arity count
- Each arity definition is enclosed in parentheses and has an argument list
- This is a way to provide default values for arguments
- Below is the general form
```
(defn multi-arity
  ; 3-arity arguments and body
  ([first-arg second-arg third-arg]
    (do-things first-arg second-arg third-arg))
  ; 2-arity arguments and body
  ([first-arg second-arg]
    (do-things first-arg second-arg))
  ; 1-arity argument and body
  ([first-arg]
    (do-thing first-arg)))
```
- In below example, "karate" is the default arg for the `chop-type` parameter
```
(defn x-chop
  "Describe the kind of chop you're inflicting on someone"
  ([name chop-type]
    (str "I " chop-type " chop " name "! Take that!"))
  ([name]
    (x-chop name "karate"))
(x-chop "Kanye West" "slap") => "I slap chop Kanye West!  Take that!"
(x-chop "Kanye East") => "I karate chop Kanye East!  Take that!"
```
- Can also have arity do completely different things such as returning a string or a funciton
```
(defn sample-arity
  ([]
    "Sam Darnold is better than Tom Brady!")
  ([number]
    (inc number)))
(sample-arity) => "Sam Darnold is better than Tom Brady!"
(sample-arity 1) => 2
```
- Rest Parameter: put the rest of the arguments in a list with the following name
- Clojure allows you to define variable-arity functions by including a rest parameter
- Rest parameter is indicated by an amersand as shown in the second line of the codeger function
- Below example maps over each name passed as an argument and generates it through the string thing
```
(defn codeger-communication
  [whippersnapper]
  (str "Get off my lawn, " whippersnapper "!!!"))
(defn codeger
  [& whippersnappers]
  (map codeger-communication whippersnappers))
(codeger-communication "Dan")
(codeger "Dan" "Danny" "Daniel" "Danielsito")
```

- Below function joins everything after the first parameter
- The example would generate "Hi, Dan, here are my favorite things: food, Eating, cooking"
```
(defn favorite-things
  [name & things]
  (str "Hi, " name ", here are my favorite things: "
       (clojure.string/join ", " things)))
(favorite-things "Dan" "food" "Eating" "cooking")
```

##### Destructing
- Let's you concisely bind names to values within a collection, such as below example
- Instructs Clojure how to associate names with values in a list, map, set, or vector
- In example, the function associates symbol `first-thing` with first element of the vector passed as an argument
```
(defn my-first
  [[first-thing]]
  first-thing)
(my-first[ "oven" "bikie" "war-axe"])
; => "oven"
```
- Vector tells Clojure that the function will receive a list/vector as an argument
-- So Clojure takes apart the argument's structure and associates meaningful names with different parts of the argument
- Destructing a vector/list you can name as many elements as you want and you use rest parameters
- Below, rest parameter of `unimportant-choices` handles any number of additional choices from the user after the first and second elements
```
(defn chooser
  [[first-choice second-choice & unimportant-choices]]
  (println (str "Your first choice is: " first-choice))
  (println (str "Your second choice is: " second-choice))
  (println (str "We're ignoring the rest of your choices. "
                "Here they are in case you need to cry over them: "
                (clojure.string/join ", " unimportant-choices))))
(chooser ["Marmalade", "Handsome Jack", "Pigpen", "Aquaman"])
; => "Your first choice is: Marmalade
; => "Your second choice is: Handsome Jack
; => We're ignoring the rest of your choices. Here they are in case\ you need to cry over them: Pigpen, Aquaman
```
- You can destructure maps the same way as a vector or list: by providing a map as a parameter
- Below prints the values of map as lat and lng
```
(defn announce-treasure-location
  ; this line tells Clojure associate lat with the :lat key
  ; and do the same for lng!
  [{lat :lat lng :lng}]
  (println (str "Treatsure lat: " lat))
  (println (str "Treasure lng: " lng)))
(announce-treasure-location  {:lat 28.22 :lng 81.33})
```
- Can breakout keywords from a map in shorter syntax
- Below is an example of the previous example but in shorter syntax:
```
(defn announce-treasure-location
  [{:keys [lat lng]}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))
(announce-treasure-location {:lat 33.22 :lng 22.11})
```
- You can retain access to original map argument using the `:as` keyword
- Below shows the original map being accessed with treasure-location
```
(defn receive-treasure-location
  [{:keys [lat lng] :as treasure-location}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))
```

###### Function Body
- Body can contain forms of any time-- Clojure returns the last form evaluated
- So in below example, `"joe"` would be returned when the function is called since it is the last form evaluated
```
(defn illustrative-function
  []
  (+ 1 304)
  30
  "joe")
(illustrative-function) ; => "joe"

(defn number)
```
- Below example shows first value being returned in a conditional
```
(defn number-comment
  [x]
  (if (> x 6)
    "Oh wow, that number is larger than 6!"
    "That number is smaller than 6!"))
; Returns first condition "Oh wow, that number is larger than 6!"
(number-comment 7)

; Returns second condition "That number is smaller than 6!"
(number-comment 5)
```
- Clojure has no priviliged functions, which means Clojure functions such as `+`  or `>` should be treated the same as functions that YOU define
- All Clojure cares about is applying functions

###### Anonymous Functions
- Clojure functions don't need to have names-- anonymous functions are used all the time
- Can create anonymous functions in two ways, the first being with `fn`
```
; general structure
(fn [param-list]
  function body)

(map (fn [name] (str "Hi, " name))
     ["Darth Vader" "Mr. Magoo"])
; => ("Hi, Darth Vader" "Hi, Mr. Magoo")

((fn [x] (* x 3)) 8)
; =>  24
```
- The second way an anonymous function can be created is using the syntax below
- In "blarg" example, `%&` is passed as a rest parameter
-- In this case, applied the identity function to the rest argument
-- ID returns the argument given without altering it
-- Rest arguments are stored as lists, so that is what is returned
-- Using this style is best if you need to write a simple anonymous function because it's visually compact
-- If you have longer more complex functions, you want to use `fn` instead
```
; % sign represents argument
#(* % 3)

; %1 and %2 can be used for multiple arguments
(#(str %1 " and " %2 " are your two arguments.") "cornbread" "grits")
; => "cornbread and grits are your two arguments."

; Can pass a rest parameter with %&
(#(identity %&) 1 "blarg" :yip)
; => (1 "blarg" :yip)

; Below example applies the function
(#(* % 3) 9)
; => 27

; Below is the map anonymous function above rewritten
(map #(str "Hi, " %)
     ["Darth Vader" "Mr. Magoo"])
; => ("Hi, Darth Vader" "Hi, Mr. Magoo")
```
- When a function returns other functions, the returned functions are called closures
- Closures: can access all variables that were in scope when the function was created
- In below example, `inc-by` is in scope so the returned function has access to it even when returned function is used outside `inc-maker`
```
(defn inc-maker
  "Create a custom incrementor"
  [inc-by]
  #(+ % inc-by))
(def inc3 (inc-maker 3))
(inc3 7)
; => 10
(inc3 14)
; => 17
```


