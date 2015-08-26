name := "brasileiraoInfo"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  "postgresql" % "postgresql" % "9.1-901-1.jdbc4",
  "org.jsoup" % "jsoup" % "0.2.1b",
  "mysql" % "mysql-connector-java" % "5.1.27"
)     

play.Project.playScalaSettings


Keys.fork in Test := false