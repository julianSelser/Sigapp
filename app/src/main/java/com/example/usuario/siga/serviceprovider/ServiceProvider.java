package com.example.usuario.siga.serviceprovider;

import android.webkit.JavascriptInterface;

import com.example.usuario.siga.JavaScriptSigaInterface;

/**
 * Interface to provide our app with data, login, etc
 *
 * Created by Julian on 03/03/16.
 */
abstract public class ServiceProvider {

    public void logIn(JavaScriptSigaInterface javaScriptSigaInterface) {
        throw new RuntimeException("Only for local use");
    };

    public void loginDataWasWrong(String errors) {
        throw new RuntimeException("Only for local use");
    }

    public abstract void attemptLogin(JavaScriptSigaInterface javaScriptSigaInterface);

    public abstract boolean isLoggedIn();

    public abstract boolean loginFailed();

    public abstract String getLoginData();
}
