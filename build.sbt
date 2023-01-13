
name := "bluebus"

version := "0.4.0-DRT"

lazy val scala = "2.13.10"

lazy val supportedScalaVersions = List(scala)

name := "bluebus"
organization := "uk.gov.homeoffice"
organizationName := "UK Home Office"
description := "Forked from https://github.com/sothach/bluebus"

crossScalaVersions := supportedScalaVersions

publishTo := {
  val artifactory = "https://artifactory.digital.homeoffice.gov.uk/"

  Some("release" at artifactory + "artifactory/libs-release")
}

libraryDependencies ++= Seq(
  "org.dispatchhttp" %% "dispatch-core" % "1.2.0",
  "org.scalatest" %% "scalatest" % "3.0.5" % Test,
  "org.mockito" % "mockito-all" % "2.0.2-beta" % Test,
  "net.jadler" % "jadler-all" % "1.3.0" % Test
)

trapExit := false
