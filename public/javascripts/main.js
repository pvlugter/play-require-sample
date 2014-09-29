// In a typical RequireJS environment, you'd set paths
// corresponding to folders in your front-end app, e.g.
// 'modules', 'views', 'templates', etc., as well as paths
// pointing to webjars.
require.config({
  baseUrl: '/assets/',
  paths: {
    // For config paths that refer directly to files, we can
    // use @routes.Assets.versioned to circumvent the lack of
    // front-end support, but only if we output the config
    // directly in a scala template.  This front-end file has no
    // knowledge of the digests so nothing will cache bust.
    'lodash': 'lib/lodash/lodash.min',

    // For config paths that refer to folders, the situation is
    // even more tricky, because we don't actually refer to
    // individual files until they are needed by another module
    // (see init.js)
    'modules': 'javascripts/modules'
  }
});
