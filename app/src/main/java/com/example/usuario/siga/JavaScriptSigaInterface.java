package com.example.usuario.siga;

import android.util.Log;
import android.webkit.JavascriptInterface;

import com.example.usuario.siga.serviceprovider.ServiceProvider;

/**
 * Interfaces with javascript
 *
 * Created by Julian on 24/01/16.
 */
public class JavaScriptSigaInterface {

    private String cip;
    private String pass;
    private boolean isLoggedIn;
    private ServiceProvider provider;

    public JavaScriptSigaInterface() {
        isLoggedIn = false;
        //TODO: initialize to null provider
    }

    public void setProvider(ServiceProvider _provider){ provider = _provider;}

    @JavascriptInterface
    public boolean isLoggedIn(){ return isLoggedIn; }

    @JavascriptInterface
    public String getCip(){ return cip; }

    @JavascriptInterface
    public String getPasswd(){ return pass; }

    @JavascriptInterface
    public String setCip(String _cip){ return cip = _cip; }

    @JavascriptInterface
    public String setPasswd(String _pass){ return pass = _pass; }

    @JavascriptInterface
    public void debugMsg(String msg){ Log.d("JS", msg); }

    @JavascriptInterface
    public void loginDataWasWrong(){ Log.d("JS", "Login data was wrong!"); }

    @JavascriptInterface
    public void attemptLogin(){
        provider.attemptLogin(this);
    }
}
