### Clojure Crash Course
- Clojure is written in a uniform structure that recognizes two kinds of structures
1. Operations
2. Literal representations (ie numbers, strings, maps, and vectors)
- Form-- refers to valid code
- Clojure evaluates every form to produce a value
- Some examples of valid forms include:
```
1
"a string"
["a" "vector" "of" "strings"]
```
- Code rarely contains free-floating literals since they don't do anything on their own
-- Instead you use literals in operations
- Operations are how you actually do things
- All operations take the form of opening parenthesis, operator, operands, closing parenthesis, for example:
```
(operator operand1 operand2 ... operandn)
```
- Instead of commas, Clojure uses whitespace to separate operands and treats commas as whitespace.  Some examples
```
(+ 1 2 3) => 6

(str "It was the panda " "in the library " "with a dust buster")
=> "It was the panda in the library with a dustbuster
```
- An invalid form would be `(+` because it doesn't have a closing parenthesis

##### Control Flow: IF, Do, and When
- `if` general structure
```
(if boolean-form
  then-form
  optional-else-form) 
```
- `if` boolean examples
```
(if true
  "By Zeus's hammer!"
  "By Aquaman's trident!") => "By Zeus's hammer!"
```
```
(if false
  "By Zeus's hammer!"
  "By Aquaman's trident!") => "By Aquaman's trident!"
```
- Similar to ternary operator in Javascript, prints first value if true, otherwise prints second value
- If pass just one argument and the conditional is false, it will return nil
```
(if false
  "By Odin's Elbow!")
; => nil
```

- `do` operator lets you wrap multiple forms in parenthesis and run each of them
```
(if true
  (do (println "Success!")
      "By Zeus's Hammer!")
  (do (println "Failure!")
      "By Aquaman's Trident!"))
; => Success! printed in REPL
; => By Zeus's hammer! returned as the value
```

- `when` is like `if` and `do` combined but with no else
- Use `when` if you want to do multiple things when something is true, but return nil when the condition is false
```
(when true
  (println "Success!")
  "abra cadabra")
; => Success!
; => "abra cadabra"
```

- Clojure recognizes true, false, and nil values-- nil indicating no value
- `nil` is also considered falsey in terms of logical representation
- Can check if a value is nil by running the `nil?` funciton
```
(nil? 1) => false
(nil? nil) => true

; "bears eat beets" is considered truthey, so it prints the string and doesn't return nil
(if "bears eat beets"
  "bears beets BAttlestar Galactica")
; => "bears beets BattleStar Galactica"

(if nil
  "This won't be the result because nil is falsey"
  "nil is falsey")
; => "nil is falsey"
```

- Clojure equality operator is `=` as seen below
```
(= 1 1) => true
(= nil nil) => true
(= 1 2) => false
```

- Clojure uses boolean operators `or` and `and`
- `or` returns first truthy value OR the last value
```
; first value returned because it's the first truthy value
(or false nil :large_I_mean_venti :why_cant_I_just_say_large)
; => :large_I_mean_venti

; no truthy found, so it returns the last value which evaluates to false
(or (= 0 1) (= "yes" "no"))
; => false

; no truthy value exists, so or returns the last value which is nil
(or nil)
; => nil
```

- `def` is uised to bind a name to a value in clojure
- AKA how we assign variables values
```
(def failed-protagonist-names
  ["Larry Potter" "Doreen the Explorer" "The Incredible Bulk"])

failed-protagonist-names
; => ["Larry Potter" "Doreen the Explorer" "The Incredible Bulk"]

(defn error-message
  [severity]
  (str "OH GOD! IT'S A DISASTER! WE'RE "
       (if (= severity :mild)
        "MILDLY INCONVENIENCED!"
        "DOOOOOOOOMED!!!)))
(error-message :mild)
; => "OH GOD! IT'S A DISASTER! WE'RE MILDLY INCONVENIENCED!"
(error-message :severe)
; => "OH GOD! IT'S A DISASTER! WE'RE DOOOOOOOMED!!!"
```

##### Data Structures
- Clojure comes with data structures, all of which are immutable-- you can't change them in place
- Cannot redeclare a data structures value the same way you'd redeclare a Ruby or Javascript variable value
- Numbers: Clojure handles anything you throw at it in terms of numbers.  Handles integers, floats, and ratios (shown below respectively)
```
93
1.2
1/5
```

- Strings: represent text like in other languages... examples below
```
"Lord Voldemort"
"\"He who must not be named\""
"\"Great cow of Mostcow!\" - Hermes Conrad"
```
- String interpolation is not a thing in Clojure, can only concat vai the str function
```
(def name "Chewbacca")
(str "\"Uggllglglglglglglll" - " name)
=> "Uggllglglglglglglll" - Chewbacca
```

- Maps: similar to dictionaries/hashes-- a way of associating value to a key
- Map values can be any type- strings, numbers, maps, vectors, or functions
- Two kinds of maps in Clojure are hash maps and sorted maps
- Empty map would be `{}`
- Map with a first and last name value in a map literal:
```
{:first-name "charlie"
 :last-name  "MCFishwich"}
```
- Map where `string-key` is associated with the `+` function: `{"string-key" +}`
- Maps can be nested: `{:name {:first "John" :middle "Jacob" :last "Jingleheimerschmidt"}}`
- Hash-map function can be used to create maps too
```
(hash-map :a 1 :b 2)
; => {:a 1 :b 2}
```
- Can retrieve a value from a hash with the blow example
```
({:name "The Human Coffeepot"} :name)
; => "The Human Coffeepot"
```
- Can retrieve value and set a default in case the searched value isn't found
- This prevents it from returning nil
```
(:d {:a 1 :b 2 :c 3} "No gnome knows homes like Noah knows")
; => "No gnome knows homes like Noah knows"
```
- Can use the `get` function to return values in maps
- `get` returns nil if it doesn't find your keyword or give it a default value
```
(get {:a 0 :b 1} :b)
; => 1
(get {:a 0 :b {:c "ho hum"}} :b)
; => {:c "ho hum"}
(get {:a 0 :b 1} :c)
; => nil
(get {:a 0 :b 1} :c "unicorns?")
; => "unicorns?"
```
- `get-in` function lets you look up values in nested maps
```
(get-in {:a 0 :b {:c "ho hum"}} [:b :c])
; => "ho hum"
```

- Keywords: used as keys in maps... some examples would be:
```
:a
:rumplestiltsken
:34
:_?
```
- Keywords can be used as functions that look up corresponding value in a data structure, for example finding a value in a map as shown above

- Vectors: similar to an array- is a 0-indexed collection
- Vector Literal: `[3 2 1]`
- Can get the 0th element of a vector (can be reused to find any value at a specified index):
```
(get [3 2 1] 0)
; => 3
```
- Can create vectors with the `vector` function
```
(vector "creepy" "full" "moon")
; => ["creepy" "full" "moon"]
```
- Can also use `conj` function to add elements to the vector, for example:
```
(conj [1 2 3] 4)
; => [1 2 3 4]
```

- Lists: similar to vectors in their linear collections of values-- BUT cannot use `get` function on a list
- To write a list literal, just insert elements into parentheses and use a single quote at the start
```
'(1 2 3 4)
; => (1 2 3 4)
```
- To retrieve element from a list you can use the `nth` function
- Note that using `nth` is slow than using `get` to retrieve an element from a vector
- This is because Clojure has to traverse all `n` elements of a list to get to the `nth` whereas it only takes a few hops to access vector element by index
```
(nth '(:a :b :c) 0)
; => :a
(nth '(:a :b :c) 2)
; => :c'
```
- Lists can be created by using the `list` function
```
(list 1 "two" {3 4})
; => (1 "two" {3 4})
```
- `conj` can also be used to add items to the beginning of a list
```
(conj '(1 2 3) 4)
; => (4 1 2 3)
```

- Sets: collections of unique values-- hash sets and sorted sets are two kinds of sets
- Literal notation for a hash set is `#{"kurt vonnegut" 20 :icicle}
- Can also use `hash-set` to create a set: `(hash-set 11 22) => #{1 2}`
- Can only have one value in a hash-set, no duplicates
- If you try to add `:b` to hash of `{:a :b}` then it will retain the same value since `:b` already exists in the set
- Can create sets from vectors/lists by using `set` function: `(set [3 3 3 4 4]) => #{3 4}`
- Can use `contains?` function to see if a value exists in a set... can also use `get`
- `contains?` returns true or false, whereas `get` will lookup if the return value exists or nil if it doesn't
```
(contains? #{:a :b} :a)
; => true

(contains? #{:a :b} 3)
; => false

(contains? #{nil} nil)
; => true
```

##### Destructuring
- Let's you concisely bind names to values within a collection
- Below example returns the first element of a collection that is passed to it
```
(defn my-first
  [[first-thing]]
  first-thing)
(my-first ["oven" "bik" "war-axe"])
=> "oven"
```
