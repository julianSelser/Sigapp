package com.example.usuario.siga.sigapi;

import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.example.usuario.siga.service.LoginService;

/**
 * Created by Julian on 12/03/16.
 */
abstract public class SigAPI {

    @JavascriptInterface
    abstract public LoginService getLoginService();
}

