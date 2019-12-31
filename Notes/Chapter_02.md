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
