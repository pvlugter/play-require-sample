name := """require-sample"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws,
  "org.webjars" % "requirejs" % "2.1.14-3",
  "org.webjars" % "lodash" % "2.4.1-4"
)

// This enables asset pipeline fingerprinting (cache invalidation)
pipelineStages := Seq(rjs, digest)
