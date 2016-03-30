package com.example.usuario.siga.service;

import android.webkit.JavascriptInterface;

/**
 * Interface to provide our app with data
 *
 * Created by Julian on 03/03/16.
 */
public interface Service {

    @JavascriptInterface
    boolean success();

    @JavascriptInterface
    boolean failure();

    @JavascriptInterface
    String getData(); //TODO: return JSON

    @JavascriptInterface
    void startQuerying();
}
