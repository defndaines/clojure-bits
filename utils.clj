(defn data-stream
  "Creates a data input stream from a file, handling gzip compression if the file is compressed."
  [file-name]
  (let [input (java.io.FileInputStream. file-name)
        header (.readShort (-> file-name java.io.FileInputStream. java.io.DataInputStream.))]
    (java.io.DataInputStream.
      (if (= 8075 header)
        (java.util.zip.GZIPInputStream. input)
        input))))

(defn acat
  "Concatenate two byte arrays."
  [left right]
  (let [lsize (count left)
        rsize (count right)
        arr (byte-array (+ lsize rsize))]
    (System/arraycopy left 0 arr 0 lsize)
    (System/arraycopy right 0 arr lsize rsize)
    arr))

(defn ip->bytes
  "Convert a string representaiton of an IP address into a byte array."
  [ip]
  (.getAddress (java.net.InetAddress/getByName ip)))

(defn record->edn
  "Produce EDN for a given Record."
  [rec]
  (str "#" (clojure.string/replace-first (type rec) "class " "") (into {} rec)))

(defn map-readers
  "Parse a namespace for all records and return a :readers map which can be handed to clojure.edn/read-string."
  [ns]
  (let [records (filter #(re-find #"^map-" (str (key %)))
                        (ns-publics ns))
        splat (fn [pair]
                (let [[k v] pair
                      rec-name (clojure.string/replace-first k #".*->" "")]
                  [(symbol (str ns "." rec-name)) (var-get v)]))]
    (into {} (map splat records))))

(defn transpose
  "Transpose a two-dimensional collection.
  (apply mapv vector coll) does not work if the last element is shorter."
  [coll]
  (loop [acc []
         c coll]
    (if (seq (first c))
      (recur (conj acc (for [row c] (first row)))
             (map rest c))
      acc))
