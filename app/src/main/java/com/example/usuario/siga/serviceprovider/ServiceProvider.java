package com.example.usuario.siga.serviceprovider;

import android.webkit.JavascriptInterface;

import com.example.usuario.siga.JavaScriptSigaInterface;

/**
 * Interface to provide our app with data, login, etc
 *
 * Created by Julian on 03/03/16.
 */
abstract public class ServiceProvider {
    public abstract void attemptLogin(JavaScriptSigaInterface sigaJsInterface);
}
