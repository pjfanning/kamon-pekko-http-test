ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.18"

val pekkoVersion = "1.7.1"
val kamonVersion = "2.8.1"

enablePlugins(JavaAgent)
javaAgents += "io.kamon" % "kanela-agent" % "2.0.0" % "runtime"

ThisBuild / resolvers += Resolver.mavenLocal

lazy val root = (project in file("."))
  .settings(
    name := "kamon-pekko-http-test",
    libraryDependencies ++= Seq(
      "org.apache.pekko" %% "pekko-actor-typed" % pekkoVersion,
      "ch.qos.logback" % "logback-classic" % "1.6.5",
      "io.kamon" %% "kamon-pekko" % kamonVersion,
      "io.kamon" %% "kamon-system-metrics" % kamonVersion,
      "io.kamon" %% "kamon-prometheus" % kamonVersion
    )
  )
