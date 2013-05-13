#### Paramount [![endorse](https://api.coderwall.com/runexec/endorsecount.png)](https://coderwall.com/runexec)

Paramount is a superiority sorter for the Clojure language.
<p></p>
An overtake states that the position of X is lesser than Y by a single position.

```clojure
paramount.core>
(apply-overtake
 (overtake 8 1)
 (range 1 11))
(2 3 4 5 6 7 8 1 9 10)
paramount.core> 
(println
 (overtaking 
  [(overtake "blue" "red")
   (overtake "green" "white")
   (overtake "yellow" "orange")]
  ["red" "yellow" "white" "blue" "orange" "green"]))
(yellow orange blue red green white)
nil
```

#### Installation

```bash
git clone https://github.com/runexec/paramount
cd paramount; lein install
```

Lein dependency

```clojure
[paramount/paramount "0.1.0-SNAPSHOT"] 
```
Use

```clojure
(use 'paramount.core)
```

### Documentation

Applying a single overtake

```clojure
paramount.core> 
;; water should always come after beer
(println 
 (apply-overtake
  (overtake :beer :water)
  [:beer :wine :voda :water]))
(:beer :water :wine :voda)
nil
paramount.core> 
;; gold should always come after silver
(println 
 (apply-overtake
  (overtake :silver :gold)
  [:gold :bronze :silver]))
(:bronze :silver :gold)
nil
paramount.core>       
;; food should aways come after candy
(println
 (apply-overtake
  (overtake :candy :food)
  [:icecream :food :fruit :candy]))
(:icecream :fruit :candy :food)
nil
```
