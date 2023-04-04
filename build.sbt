
name := "bluebus"

ThisBuild / version := "v" + sys.env.getOrElse("DRONE_BUILD_NUMBER", sys.env.getOrElse("BUILD_ID", "DEV"))

scalaVersion := "2.13.10"

ThisBuild / scapegoatVersion := "2.1.1"

name := "bluebus"
organization := "uk.gov.homeoffice"
organizationName := "UK Home Office"
description := "Forked from https://github.com/sothach/bluebus"

val artifactory = "https://artifactory.digital.homeoffice.gov.uk/"


libraryDependencies ++= Seq(
  "org.dispatchhttp" %% "dispatch-core" % "1.2.0",
  "org.mockito" % "mockito-all" % "2.0.2-beta" % Test,
  "net.jadler" % "jadler-all" % "1.3.0" % Test,
  "org.scalatest" %% "scalatest" % "3.2.15" % Test,
)

publishTo := Some("release" at artifactory + "artifactory/libs-release")

trapExit := false
