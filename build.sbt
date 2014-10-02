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

// Create a map of versioned assets, replacing the empty versioned.js
DigestKeys.indexPath := Some("javascripts/versioned.js")

// Override the requirejs.load function to use versioned assets.
// Use the requirejs config.baseUrl to rebase the urls.
// Can also use `SbtDigest.writeJsIndex("/assets/")(index)` instead.
DigestKeys.indexWriter := { index =>
  s"""
(function() {
  var index = ${SbtDigest.writeJsIndex(index)};
  function versioned(baseUrl, url) {
    var rebasedUrl = url.replace(new RegExp('^' + baseUrl), '');
    var versionedUrl = index[rebasedUrl];
    return versionedUrl ? (baseUrl + versionedUrl) : url;
  }
  var requireLoad = requirejs.load;
  requirejs.load = function (context, id, url) {
    var loadUrl = versioned(context.config.baseUrl, url);
    return requireLoad.apply(requirejs, [context, id, loadUrl]);
  };
})();
"""
}

// Exclude js maps and srcs from fingerprinting
excludeFilter in digest := HiddenFileFilter || "*.js.map" || "*.js.src.js"
