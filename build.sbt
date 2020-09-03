
name := "bluebus"

version := "0.3.1-DRT"

lazy val scala212 = "2.12.8"
lazy val scala211 = "2.11.12"
lazy val supportedScalaVersions = List(scala212, scala211)

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
  "net.databinder.dispatch" %% "dispatch-core" % "0.13.4",
  "org.scalatest" %% "scalatest" % "3.0.5" % Test,
  "org.mockito" % "mockito-all" % "2.0.2-beta" % Test,
  "net.jadler" % "jadler-all" % "1.3.0" % Test)

trapExit := false
