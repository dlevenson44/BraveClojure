##### Chapter 2: Emacs
- Recommended due to tight integration with Clojure REPL, lets you immediately test out code as you write it
- This is useful while learning and writing programs
- Emacs is great for any Lisp dialect and is designed for it
- Run Emacs by running `emacs`, can run `emacs -nw --color=no` for no color, or `emacs --daemon` to run it as a daemon (would then connect to daemon by running `emacsclient -nc`)
- All emacs keybindings can be remapped as needed

To configure Emacs for Clojure, first install Emacs on OS, run to make sure it's installed, then close Emacs
- Next delete `~/.emacs` and `~/.emacs/d`-- this is where Emacs config files live
- Next download Emacs config file from https://github.com/flyingmachine/emacs-for-clojure/archive/book1.zip
-- If above link doesn't work, check instructions at https://github.com/flyingmachine/emacs-for-clojure
- Once downloaded and extracted, run `mv PATH/TO/EXTRACTEDFILE/emacs-for-clojure-book1 ~/.emacs.d`

- Ctrl-G: quits whatever Emacs command you're trying to run-- won't close Emacs but will cancel current action (AKA Emacs Escape Hatch)
- Editing happens in Emacs buffer, first buffer is named `*scratch*` when it is first opened
-- `*scratch*` buffer handles parentheses and indentation so it's optimal for Lisp but bad for plain text
-- Create a buffer so we can play without unexpected things happening--- 
1. Hold Ctrl press X
2. Release Ctrl
3. Press B
- This process is also called C-X-B
4. This opens prompt at bottom of app called Minibuffer-- where Emacs prompts for input
- In this case, it's prompting for a buffer name-- can type name in and press
- C-X-B allows you to create as many buffers as you want and switch between existing ones
- New buffers only exist only in memory until you save it as a file

- Can open files with `c-x c-f` wher you need to hold Ctrl down when pressing X and F
- Can also create new files with the above command
- This gives another minibufferprompt
- Can save files with `c-x c-s`

- Emacs Mode:  collection of key bindings and functions that are packaged to help with productivity when editing different types of files
-- IE: Clojure file type will want Clojure mode, Markdown file wants Markdown mode, etc
- Major mode would but Clojure or Markdown, whereas Minor modes are functionality accross file type
- Can only have on major mode on at a time

- Many modes distributed via packages
- Emacs 24 makes it easy to browse/install package
- `M-x package-list-packages` show every available package, just run `M-x package-refresh-contents` to make sure you have latest list

##### Terminology and Key Bindings
- Point: your current position within the editor (the flashing red block, aka the cursor)

- `c-k` runns kill line, which kills all text after cursor on the current line
- `c-/` to undo a
- `c-a` to move to start of line
- `c-e` to move to end of line
- `M-m` move to first non0whitespace character on line
- `c-f` to move forward a character, `c-b` to move back
- `m-f` and `m-b` to move forward/back a word at a time
- `c-s` to search for word going forward, `c-r` to search in reverse
- `m-g g` go to specific line

- Don't select text, instead we create regions
- Set mark with C+Space, then move point to add text to region, 
- Can set region of text, use `m-x replace-string` and replace face with head

- Instead of copy/cut/paste, we create kill rings that store removed text, and paste it by yanking the most recent entry from the ring
- Can store multiple blocks of text on the ring and you can cycle through them so you know what you're yanking!
- `m-w` is like copy, `m-d` is like cut, `c-y` is like paste, use `M-y` to remove previously yanked value, and replace it with the next yank value
- `c-w` to kill region, `m-w` to copy region to kill ring
- `Tab` to indent line
- `c-j` is new line and indent, equivalent to enter + tab
- `m-/` hippie expand; cycles through possible expansions of text before point
- `m-\` delete all spaces and tabs around point (think linting)
- `c-h h key-binding` describe function bound to the specified key binding
- `c-h f` describe function

##### Emacs with Clojure
- Need to install Emacs packageCIODER to connect Emacs to a REPL
- Can install by running `M-x package-install cider`
- Allows us to start a REPL within Emacs
1. Open file with `c-x c-f`
2. Run `m-x cider-jack-in` to open REPL within Emacs

- `c-x o` switches cursor to another window
- `c-x 1` deletes all other windows, doesn't close buffer
- `c-x 2` split frame above and below
- `c-x 3` split frame side by side
- `c-x 0` delete current window
- Can navigate to end of line and pre `c-x c-e` and it will print the statement in the REPL
-- Runs cider-eval-last-expression-- sends expression to be evaluated by REPL

- Testing code workflow: Open file and REPL, add new function, save file (`c-x c-s`), then compile with `c-c c-k`
-- After doing this, you can now call all new functions from the REPL
-- Any existing functions that may have been changed wil also be updated after being compiled

- `c-c m-n m-n` switch to namespace of current buffer
- `c-x c-e` evaluate expression
- `c-c c-k` compile current buffer
- `c-c c-d c-d` display doc for symbol under point
- `m-.` and `m-,` navigates to source code for symbol under point and return you to original buffer
- `c-c c-d c-a` apropros search-- find arbitrary text across function names and documentation
- `c and up/down arrows` cycle through REPL history
- `c-enter` close parentheses and evaluate (seems to be unnecssary in current version of EMacs)

- When you throw bad syntax into REPL, such as `(map)`, it throws Errors in REPL buffer and opens new window
- New window displays the Stack Trace: shows function that threw exception and which function called that function (similar to React error tree)
- CIDER gives you a tool to filter stack traces to reduce the noise so you can zero in on teh cause of your exception
- Line 2 of `*cider-error*` buffer has filter CLojure, Java, REPL, Tooling, Duplicates, and All
- Can click each option to activate the filter, can also click each stack trace line to jump to corresponding source code
- Close stack trace by pressing `q`

##### Paredit-Mode
- A minor mode that turns parentheses from a liability into an asset
- Ensures all parentheses, double quotes, and brackets are closed
- Offers key bindings to navigate/alter structure created by parentheses
- Full cheatsheet can be found at: https://github.com/georgek/paredit-cheatsheet/blob/master/paredit-cheatsheet.pdf
- Can disable with `m-x paredit-mode` if needed (can also re-enable with this command)

- Wrapping: surrounds expression after point with parentheses
- Slurping: moves closing parenthesis to include the next expression to the right
- To get form `(+ 1 2 3 4)` to (+ 1 (* 2 3) 4)--> wrap the `2` with an asterisk, then slurp the `3`
- Go from `(+ 1 2 3 4)` to `(+ 1 |2 3 4)`, then type `m-(` for `paredit-wrap-round`... generates `(+1 (|2) 3 4)
- Add asterisk to generate `(+ 1 (* |2) 3 4)`, then slurp the `3` with `C-right arrow` to generate `(+ 1 (* |2 3) 4)`

- Barfing: Reverses a slurp, aka unslurp
- Off above example, if you accidentally slurp 4 to generate `(+ 1 (|* 2 3 4))`, you can use `C-left arrow` to barf it up

- You will frequently have blocks of code with lots of parentheses
- Can use `c-m-f` on an opening parenthesis to take you to a closing one
- Can also use `c-m-b` to go from a closing parenthesis to an opening one\
- `m-x paredit-mode` toggle paredit mode
- `m-(` surround expression after point in parentheses
- `c- left/right arrow` barf (move closing parenthesis to left to exclude last expression)/slurp (move closing parenthesis to the right to include in next expression)
- `c-m-f`, `c-m-b`- move to opening/closing parenthesis


##### Emacs Resources
- EMacs Manual:  http://www.gnu.org/software/emacs/manual/html_node/emacs/index.html#Top
- Emacs Reference Card (Cheatsheet): http://www.ic.unicamp.br/~helio/disciplinas/MC102/Emacs_Reference_Card.pdf
- Mastering Emacs: http://www.masteringemacs.org/reading-guide/
- Begginers Guide to Emacs 24 or Later: http://sachachua.com/blog/wp-content/uploads/2013/05/How-to-Learn-Emacs8.png
- Can also press `c-h t` within Emacs for the built-in tutorial

