(ns sftp
  "Sample code using JSch library to SFTP a file."
  ;; [com.jcraft/jsch "0.1.55"]]
  (:import [com.jcraft.jsch JSch JSchException SftpException ChannelSftp]))

(def username "ftpuser")
(def host "ftp.destination.net")
(def password "s00p3rS3cR3T")

(def file-name "/tmp/file.xml")
(def to-name "file.xml")

(defn establish-session
  [^JSch ssh host username password]
  (let [session (.getSession ssh username host 22)]
    (.setConfig session "StrictHostKeyChecking" "no")
    (.setPassword session password)
    session))

(let [ssh (JSch.)
      session (establish-session ssh host username password)]
  (try
    (.connect session)
    (let [channel (.openChannel session "sftp")]
      (try
        (.connect channel)
        (.put channel file-name to-name)
        (.exit session)
        (catch JSchException e (str "JSch Problem: " (.getMessage e)))
        (catch SftpException e (str "SFTP Problem: " (.getMessage e)))
        (finally (.disconnect channel))))
    (catch JSchException e (str "JSch Problem: " (.getMessage e)))
    (catch SftpException e (str "SFTP Problem: " (.getMessage e)))
    (finally (.disconnect session))))
