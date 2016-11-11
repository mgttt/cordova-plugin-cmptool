//BeforePluginInstall_android.js
var fs=null;
function sync_copy(src,tgt){
	if(fs==null)return false;
	console.log('to copy '+src+' => '+tgt);
	return fs.writeFileSync(tgt, fs.readFileSync(src));
}
module.exports = function(ctx) {
	console.log("process.args");
	console.log(process.argv);

	console.log(" ctx ", ctx);

	// make sure android platform is part of build
	//if (ctx.opts.platforms.indexOf('android') < 0) {
	//	return;
	//}

	fs = ctx.requireCordovaModule('fs');
	var path = ctx.requireCordovaModule('path');
	var deferral = ctx.requireCordovaModule('q').defer();

	//console.log(" process.cwd() ", process.cwd());
	//console.log(" opts.plugin ", ctx.opts.plugin);
	return deferral.promise;

	var platformRoot = path.join(ctx.opts.projectRoot, 'platforms/android');
	//var pluginRoot =ctx.opts.plugin.dir;
	console.log("scriptLocation=",ctx.scriptLocation);
	var srcRoot = path.join(path.dirname(ctx.scriptLocation),
		'/../app-hybrid-core/lib-android/szu.bdi.hybrid.core/src');
	
	var tgtRoot = path.join(platformRoot, 'src');

	sync_copy(srcRoot +'src/main/java/szu/bdi/hybrid/core/JSO.java'
	,tgtRoot +'JSO.java');

	//var apkFileLocation = path.join(platformRoot, 'build/outputs/apk/android-debug.apk');

	//fs.stat(apkFileLocation, function(err,stats) {
	//	if (err) {
	//		deferral.reject('Operation failed');
	//	} else {
	//		console.log('Size of ' + apkFileLocation + ' is ' + stats.size +' bytes');
	//		deferral.resolve();
	//	}
	//});

	return deferral.promise;
};
/*
 *
    // Skip processing if being called from within Visual Studio or MSBuild
    if (!process.env["VisualStudioEdition"]) {
        fs = require('fs');
        path = require('path');

        context.opts.cordova.platforms.forEach(function(platform) {
            console.log("Processing res/native for " + platform);
            var resNative = path.join(process.cwd(), "res", "native", platform);
            if (fs.existsSync(resNative)) {
                copyFiles(resNative, path.join(process.cwd(), "platforms", platform));
            }
        });            
    } else {
        console.log("Build running inside of MSBuild or Visual Studio - skipping res/native hook given built in support.");
    }
    // Recusive copy function for res/native processing
    function copyFiles(srcPath, destPath) {
        if (fs.statSync(srcPath).isDirectory()) {
            if (!fs.existsSync(destPath)) {
                fs.mkdirSync(destPath);
            }
            fs.readdirSync(srcPath).forEach(function (child) {
                copyFiles(path.join(srcPath, child), path.join(destPath, child));
            });
        } else {
            fs.writeFileSync(destPath, fs.readFileSync(srcPath));
        }
    }  
*/
