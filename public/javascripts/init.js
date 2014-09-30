// This is a valid require call.  The config path 'modules' will
// route this dependency to the correct file: '/assets/javascripts/modules/module1'.
// However, without specifying a path for each module explicitly in
// the require config, versioning won't work here either.
// In other words, without specifying a config path that points to the file
// 'module1.js' explicitly, we'd have to have the versioning present in every
// single javascript file that loads module1, e.g.:
//   require(['modules/a95c530a7af5f492a74499e70578d150-module1'], function(module1) {
require(['versioned!modules/module1'], function(module1) {
  module1.start();
});
