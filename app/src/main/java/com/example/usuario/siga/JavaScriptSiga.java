package com.example.usuario.siga;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

/**
 * Created by usuario on 24/01/16.
 */
public class JavaScriptSiga {

    JavaScriptSiga(Context c, WebView w) {
    }

    @JavascriptInterface
    public String getCip(){
        return "cip";
    }

    @JavascriptInterface
    public String getPasswd(){
        return "pass";
    }

    @JavascriptInterface
    public void debugMsg(String msg){
        Log.d("JS", msg);
    }

    @JavascriptInterface
    public void loginDataWasWrong(){
        Log.d("JS", "Login data was wrong!");
    }
}
