(ns paramount.core)

(defn overtake [x y]
  {:before y :after x})

(defn possible?
  "Test if an overtake is possible"
  [overtake coll]
  (let [{:keys [before after]} overtake
        coll (-> coll distinct vec)]
    (every? seq
            [(filter #(= % before) coll)
             (filter #(= % after) coll)])))

(defn apply-overtake
  [overtake coll]
  (if-not (possible? overtake
                     coll)
    coll
    (let [{:keys [before after]} overtake
          ;; Safety is added to end of coll to prevent
          ;; errors when nothing is on either side of
          ;; overtaken. Removed on return
          safety (gensym)
          after-seq (->> coll
                         distinct
                         vec
                         (#(conj % safety))
                         (#(cons safety %))
                         (split-with #(not= after %)))
          after-seq (if (seq (last after-seq))
                      [(first after-seq)
                       (-> after-seq last rest)]
                      (first after-seq))
          after-seq [(->> (first after-seq)
                          (filter #(not= % before)))
                     after
                     before
                     (->> after-seq
                          last
                         (filter #(not= % before)))]]
      (->> after-seq
           (reduce #(if (coll? %2)
                      (apply conj %1 %2)
                      (conj %1 %2))
              [])
           drop-last
           rest))))

(defn overtaking 
  [overtake-coll coll]
  (with-local-vars [o overtake-coll
                    c coll]
    (while (seq @o)
      (var-set c (apply-overtake (first @o) @c))
      (var-set o (rest @o)))
    @c))
               

