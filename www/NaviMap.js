var exec = require('cordova/exec');

var api = {};

api.amapRoute = function(uri, success, error) {
    exec(success, error, "NaviMap", "amapRoute", [uri]);
};

api.bdmapRoute = function(uri, success, error) {
    exec(success, error, "NaviMap", "bdmapRoute", [uri]);
};

module.exports = api;
