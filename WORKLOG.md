# work log for reference only (please ignore)
```
#git -C lib-ios-jso pull || git clone https://github.com/SZU-BDI/lib-ios-jso.git lib-ios-jso

git -C lib/lib-ios-jso pull || git clone https://github.com/SZU-BDI/lib-ios-jso.git lib/lib-ios-jso

```


# work log

prepare tools (plugman)
```
npm install -g plugman
```

when create 0.0.1
```
#rm -Rf cmptool/

#plugman create --name cmptool --plugin_id cmp.cordova.cmptool --plugin_version 0.0.1 
#cd cmptool/

plugman platform add --platform_name ios
plugman platform add --platform_name android

cd ../$proj/
cordova plugin rm cmp.cordova.cmptool
#cordova plugin add ../cmptool/
#cordova plugin add https://github.com/wanjochan/cordova-plugin-cmptool.git
cordova plugin ls
```

build to see if ok
```
#cordova platform rm ios
#cordova platform add ios
cordova build ios

#cordova platform rm android
#cordova platform add android
cordova build android
```

try run to see if ok
```
cordova run ios
cordova run ios --debug --emulator --target="iPhone-4s, 8.1"
cordova run android
```

update plugin and again
```
#1. change codes in ../cmptool/src/ and remember change version in plugin.xml if a milesone.

#2. install the plugin again
cordova plugin rm cmp.cordova.cmptool
cordova plugin add ../cmptool/

cordova plugin ls

#3. run again
#cordova run android
cordova run android --debug
cordova run ios --release --emulator --target="iPhone-4s, 8.1"
```

