package cmp.cordova;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//TODO 依赖后 可以extends cmp.cordova.? 顺便测试下代码空间...

public class cmptool extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        //if (action.equals("coolMethod")) {
        //    String message = args.getString(0);
        //    this.coolMethod(message, callbackContext);
        //    return true;
        //}
        if (action.equals("pingpong")) {
            String message = args.getString(0);
            this.pingpong(message, callbackContext);
            return true;
        }
        if (action.equals("startui")) {
            String message = args.getString(0);
            this.startUi(message, callbackContext);
            return true;
        }
        return false;
    }

    //private void coolMethod(String message, CallbackContext callbackContext) {
    //    if (message != null && message.length() > 0) {
    //        callbackContext.success(message);
    //    } else {
    //        callbackContext.error("Expected one non-empty string argument.");
    //    }
    //}
    private void pingpong(String message, CallbackContext callbackContext) {
			//TODO using JSONObject to add a callback
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
    private void startUi(String message, CallbackContext callbackContext) {

			//通过之前HybridCore的结构来实现些返回动作：从config.json找到要生成的UI类、加载、返回结果等
			//TODO using JSONObject to add a callback
        if (message != null && message.length() > 0) {
            callbackContext.success("TODO 准备制作启动新原生UI并关闭后返回值...");
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
}
