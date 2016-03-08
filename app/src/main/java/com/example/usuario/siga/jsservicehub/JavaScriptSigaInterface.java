package com.example.usuario.siga.jsservicehub;

import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.example.usuario.siga.service.crawlerservices.login.CrawlerLoginService;

/**
 * Interfaces with javascript
 *
 * Created by Julian on 24/01/16.
 */
public class JavaScriptSigaInterface implements JsServiceHub {

    private CrawlerLoginService loginProvider;
    private WebView crawler;

    public void setLoginProvider(CrawlerLoginService _loginProvider){ loginProvider = _loginProvider; }

    public void setCrawler(WebView _crawler){ crawler = _crawler; }

    @Override
    public CrawlerLoginService getLoginService(){
        return loginProvider;
    }

    @JavascriptInterface
    public void debugMsg(String msg){ Log.d("JS", msg); }
}
