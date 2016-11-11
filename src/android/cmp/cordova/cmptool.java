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
		//TODO 
		//参数分析，取出 name 
		//在assets/config.json上取出 name 对应的 clsName
		//Class.forName(clsName) 取出对应的类
		//塞参数然后丢过去
		/*
			//Intent intent = new Intent(caller, targetClass);
			String uiData_s = o2s(callParam);

			intent.putExtra("uiData", uiData_s);
			try {
				caller.startActivityForResult(intent, 1);//onActivityResult()
			} catch (Throwable t) {
				quickShowMsgMain("Error:" + t.getMessage());
			}
		 注意还要处理 call back !!!
		*/
		public static void startUi(String name, String overrideParam_s, Activity caller) {
			Object uia = getAppConfig(UI_MAPPING);
			if (uia == null) {
				HybridTools.quickShowMsgMain("config.json error!!!");
				return;
			}
			JSONObject defaultParam = ((JSONObject) uia).optJSONObject(name);
			if (defaultParam == null) {
				HybridTools.quickShowMsgMain("config.json not found " + name + " !!!");
				return;
			}

			JSONObject overrideParam = s2o(overrideParam_s);
			JSONObject callParam = basicMerge(defaultParam, overrideParam);
			Log.v(LOGTAG, "param after merge=" + callParam);

			String clsName = callParam.optString("class");
			if (isEmptyString(clsName)) {
				HybridTools.quickShowMsgMain("config.json error!!! config not found for name=" + name);
				return;
			}
			Class targetClass = null;
			try {
				//reflection:
				targetClass = Class.forName(clsName);
				Log.v(LOGTAG, "class " + clsName + " found for name " + name);
			} catch (ClassNotFoundException e) {
				HybridTools.quickShowMsgMain("config.json error!!! class now found for " + clsName);
				return;
			}

			Intent intent = new Intent(caller, targetClass);

			try {
				if (!isEmptyString(name)) {
					callParam.put("name", name);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			String uiData_s = o2s(callParam);

			intent.putExtra("uiData", uiData_s);
			try {
				caller.startActivityForResult(intent, 1);//onActivityResult()
			} catch (Throwable t) {
				quickShowMsgMain("Error:" + t.getMessage());
			}
		}
}
