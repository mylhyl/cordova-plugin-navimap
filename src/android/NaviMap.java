package com.contron.cordova.navimap;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by hupei on 2017/10/11.
 */

public final class NaviMap extends CordovaPlugin {
    private Context mContext;
    private CallbackContext mCallback;

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        mContext = cordova.getActivity();
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        mCallback = callbackContext;
        //高德地图
        if (action.equalsIgnoreCase("amapRoute")) {
            amapRoute(args);
            return true;
        }
        //百度地图
        if (action.equalsIgnoreCase("bdmapRoute")) {
            bdmapRoute(args);
            return true;
        }
        return false;
    }

    private void amapRoute(JSONArray args) throws JSONException {
        String uriString = args.getString(0);
        if (TextUtils.isEmpty(uriString)) {
            sendEmpty();
        } else {
			boolean appInstalled = isAppInstalled(mContext, "com.autonavi.minimap");
			if(appInstalled){
                Intent intent = new Intent();

                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setPackage("com.autonavi.minimap");

                Uri uri = Uri.parse(uriString);
                intent.setData(uri);
                try {
                    mContext.startActivity(intent);
                    sendSuccess();
                } catch (Exception e) {
                    e.printStackTrace();
                    sendError();
                }
			}else{
				mCallback.error("未检测到高德地图");
			}
        }
    }

    private void bdmapRoute(JSONArray args) throws JSONException {
        String uriString = args.getString(0);
        if (TextUtils.isEmpty(uriString)) {
            sendEmpty();
        } else {
            boolean appInstalled = isAppInstalled(mContext, "com.baidu.BaiduMap");
			if(appInstalled){
                Intent intent = new Intent();
                intent.setPackage("com.baidu.BaiduMap");

                Uri uri = Uri.parse(uriString);
                intent.setData(uri);
                try {
                    mContext.startActivity(intent);
                    sendSuccess();
                } catch (Exception e) {
                    e.printStackTrace();
                    sendError();
                }
            }else{
                mCallback.error("未检测到百度地图");
            }
        }
    }
	
    private boolean isAppInstalled(Context context, String uri) {
        PackageManager pm = context.getPackageManager();
        boolean installed;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            installed = false;
        }
        return installed;
    }
	
    private void sendEmpty() {
        mCallback.error("参数不能为空");
    }

    private void sendSuccess() {
        mCallback.success("启动成功");
    }

    private void sendError() {
        mCallback.error("启动失败");
    }
}
