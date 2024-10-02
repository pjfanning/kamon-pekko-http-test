ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.15"

val pekkoVersion = "1.1.1"
val kamonVersion = "2.7.4"

enablePlugins(JavaAgent)
javaAgents += "io.kamon" % "kanela-agent" % "1.0.18" % "runtime"

lazy val root = (project in file("."))
  .settings(
    name := "kamon-pekko-http-test",
    libraryDependencies ++= Seq(
      "org.apache.pekko" %% "pekko-actor-typed" % pekkoVersion,
      "ch.qos.logback" % "logback-classic" % "1.3.14",
      "io.kamon" %% "kamon-pekko" % kamonVersion,
      "io.kamon" %% "kamon-system-metrics" % kamonVersion,
      "io.kamon" %% "kamon-prometheus" % kamonVersion
    )
  )
