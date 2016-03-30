package com.example.usuario.siga.sigapi;

import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.usuario.siga.service.crawlerservices.login.CrawlerLoginService;

/**
 * Created by Julian on 12/03/16.
 */
public class AndroidCrawlerSigAPI extends SigAPI {
    private CrawlerLoginService loginService;

    public AndroidCrawlerSigAPI(CrawlerLoginService loginService){
        this.loginService = loginService;
    }

    @JavascriptInterface
    public void debugMsg(String msg){
        Log.d("JS", msg);
    }

    @Override
    public CrawlerLoginService getLoginService() {
        return loginService;
    }

    public AndroidCrawlerSigAPI addItselfAsJsInterfaceTo(WebView webView){
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSavePassword(false);

        webView.addJavascriptInterface(this, "SigAPI");

        return this;
    }
}
