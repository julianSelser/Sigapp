package com.example.usuario.siga.service;

import android.content.SharedPreferences;
import android.webkit.JavascriptInterface;

/**
 * Interface to all login services
 *
 * Created by Julian on 11/03/16.
 */
public abstract class LoginService implements Service {

    protected String cip = "";
    protected String pass = "";
    protected SharedPreferences credentialStore;

    public LoginService(SharedPreferences _credentialStore){
        credentialStore =_credentialStore;
    }

    //TODO: allow for passing these in a json
    @JavascriptInterface
    public String getCip(){ return cip; }
    @JavascriptInterface
    public String getPasswd(){ return pass; }
    @JavascriptInterface
    public String setCip(String _cip){ return cip = _cip; }
    @JavascriptInterface
    public String setPasswd(String _pass){ return pass = _pass; }

    @JavascriptInterface
    public LoginService clearPersistentCredentials(){
        saveCipAndPass(null, null);

        return this;
    }

    @JavascriptInterface
    public void saveCipAndPass(String cip, String pass){
        SharedPreferences.Editor editor = credentialStore.edit();

        editor.putString("cip", cip);
        editor.putString("pass", pass);

        editor.commit();
    }

    public boolean thereIsNoSavedCredentials() {
        return credentialStore.getString("cip", "").isEmpty()
                || credentialStore.getString("pass", "").isEmpty();
    }
}
