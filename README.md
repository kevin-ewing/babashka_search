# Babashka Search

## Setup
Bababska must be insatalled, visit for instructions on how to do that https://github.com/babashka/babashka.

## Aliasing Babashka Search
In `.bash_profile` add

```
#Babashka Search
alias bbsearch="bb --classpath <Absolute/Path/To/Babashka/Search/src/folder> --main core "<Path/To/Home/Directory>" $1"
```

For me it looks like:
```
#Babashka Search
alias bbsearch="bb --classpath /Users/kewing/Desktop/repos/babashka_search/src --main core "/Users/kewing" $1"
```

## Using Babashka Search
```
$ bbsearch "test.py"

test.py
   Path: /Users/kewing/one/test.py
   Size: 48096 bytes
test.py
   Path: /Users/kewing/two/test.py
   Size: 9490 bytes
test.py
   Path: /Users/kewing/three/test.py
   Size: 222 bytes
test.py
   Path: /Users/kewing/four/test.py
   Size: 9490 bytes
```
