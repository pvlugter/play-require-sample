define(['versions'], function (versions) {
  return {
    load: function (name, req, onload, config) {
      var url = req.toUrl(name)
      var loadName = url.replace(new RegExp('^' + config.baseUrl), '');
      var versioned = versions && versions[loadName] || name;
      req([versioned], function (value) {
        onload(value);
      });
    }
  };
});
