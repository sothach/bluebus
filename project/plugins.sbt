ThisBuild / libraryDependencySchemes ++= Seq(
  "org.scala-lang.modules" %% "scala-xml" % VersionScheme.Always
)
addSbtPlugin("com.github.sbt" % "sbt-native-packager" % "1.9.16")
addSbtPlugin("org.scoverage" % "sbt-scoverage" % "2.0.6")
addSbtPlugin("org.scalastyle" % "scalastyle-sbt-plugin" % "1.0.0")
addSbtPlugin("com.sksamuel.scapegoat" %% "sbt-scapegoat" % "1.1.1")
addSbtPlugin("net.vonbuchholtz" %% "sbt-dependency-check" % "5.0.0")
addDependencyTreePlugin