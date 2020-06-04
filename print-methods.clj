(defmethod print-method java.time.LocalDate
  [v ^java.io.Writer w]
  (.write w "#date \"")
  (.write w (.format v java.time.format.DateTimeFormatter/ISO_LOCAL_DATE))
  (.write w "\""))

(java.time.LocalDate/now)

(defn format-instant
  [v ^java.io.Writer w]
  (.write w "#inst \"")
  (.write w (.format v java.time.format.DateTimeFormatter/ISO_INSTANT))
  (.write w "\""))

(defmethod print-method java.time.ZonedDateTime
  [v ^java.io.Writer w]
  (format-instant v w))

(defmethod print-method java.time.Instant
  [v ^java.io.Writer w]
  (format-instant (.atZone v (java.time.ZoneId/of "UTC")) w))

(java.time.ZonedDateTime/now)

(java.time.Instant/now)
