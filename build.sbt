
name := "bluebus"

version := "0.3.4-DRT"

val scala212 = "2.12.8"

ThisBuild / scapegoatVersion := "1.4.2"
scalaVersion := scala212

name := "bluebus"
organization := "uk.gov.homeoffice"
organizationName := "UK Home Office"
description := "Forked from https://github.com/sothach/bluebus"

resolvers += "Artifactory Realm" at "https://artifactory.digital.homeoffice.gov.uk/"
resolvers += "Artifactory Realm sonatype cache" at "https://artifactory.digital.homeoffice.gov.uk/artifactory/sonatype-release-cache/"
resolvers += "Secured Central Repository" at "https://repo1.maven.org/maven2"
credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

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
