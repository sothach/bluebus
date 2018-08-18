name := "bluebus"

version := "0.2"

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  "net.databinder.dispatch" %% "dispatch-core" % "0.13.4",
  "org.scalatest" %% "scalatest" % "3.0.5" % Test,
  "org.mockito" % "mockito-all" % "2.0.2-beta" % Test,
  "net.jadler" % "jadler-all" % "1.3.0" % Test)

trapExit := false