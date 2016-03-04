package com.example.usuario.siga.serviceprovider.webcrawler;

import android.webkit.WebView;

import com.example.usuario.siga.JavaScriptSigaInterface;

/**
 * Created by Julian on 04/03/16.
 */
public class DummyWebCrawlerServiceProvider extends WebCrawlerServiceProvider {
    public DummyWebCrawlerServiceProvider(WebView _crawlerWebView, ScriptInjectorWebClient injector) {
        super(_crawlerWebView, injector);

        injector.addJsUrlMap("/android_asset/www/html/crawler/login.html", "www/js/crawler/login.js");
        injector.addJsUrlMap("/android_asset/www/html/crawler/siga.html", "www/js/crawler/dataExtractor.js");
    }

    @Override
    public void attemptLogin(JavaScriptSigaInterface sigaJsInterface) {
        crawlerWebView.loadUrl("file:///android_asset/www/html/crawler/login.html");
    }
}
