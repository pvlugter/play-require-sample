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

// Create a map of versioned assets, replacing the empty versions.js
DigestKeys.indexPath := Some("javascripts/versions.js")

// Wrap the asset index in a requirejs module definition and strip .js extensions
DigestKeys.indexWriter := { index =>
  val extensionless = index map { case (p, v) => p.stripSuffix(".js") -> v.stripSuffix(".js") }
  "define(" + SbtDigest.writeJsIndex(extensionless) + ");"
}

// Exclude js maps and srcs from fingerprinting
excludeFilter in digest := HiddenFileFilter || "*.js.map" || "*.js.src.js"
