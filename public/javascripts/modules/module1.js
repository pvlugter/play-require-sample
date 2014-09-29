// Versioning will work OK here because the path we've specified
// refers to a config path that points to a specific single file
// (in this case, '/assets/lib/lodash/lodash.min.js')
define(['lodash'], function(_) {
  return {
    name: 'module1',
    start: function() {
      window.console && window.console.log(this.name + ' started');
    }
  };
});
