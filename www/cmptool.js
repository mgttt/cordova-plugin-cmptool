var exec = require('cordova/exec');

//exports.coolMethod = function(arg0, success, error) {
//    exec(success, error, "mkcnative", "coolMethod", [arg0]);
//};

exports.pingpong = function(arg0, success, error) {
    exec(success, error, "cmptool", "pingpong", [arg0]);
};

exports.startui = function(arg0, success, error) {
    exec(success, error, "cmptool", "startui", [arg0]);
};
