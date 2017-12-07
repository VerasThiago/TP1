name := "rest"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
    javaEbean,
    cache
)


libraryDependencies += javaJdbc

libraryDependencies += jdbc

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.41"

play.Project.playJavaSettings
