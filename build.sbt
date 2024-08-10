ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.14"

val pekkoVersion = "1.1.0-M1"
val kamonVersion = "2.7.3"

enablePlugins(JavaAgent)
javaAgents += "io.kamon" % "kanela-agent" % "1.0.18" % "runtime"

lazy val root = (project in file("."))
  .settings(
    name := "kamon-pekko-http-test",
    libraryDependencies ++= Seq(
      "org.apache.pekko" %% "pekko-actor-typed" % pekkoVersion,
      "org.apache.pekko" %% "pekko-http" % pekkoVersion,
      "org.apache.pekko" %% "pekko-stream" % pekkoVersion,
      "ch.qos.logback" % "logback-classic" % "1.3.14",
      "io.kamon" %% "kamon-pekko" % kamonVersion,
      "io.kamon" %% "kamon-system-metrics" % kamonVersion,
      "io.kamon" %% "kamon-prometheus" % kamonVersion
    )
  )
