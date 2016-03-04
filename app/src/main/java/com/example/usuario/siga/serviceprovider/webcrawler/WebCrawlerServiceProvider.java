package com.example.usuario.siga.serviceprovider.webcrawler;

import android.util.Log;
import android.webkit.WebView;

import com.example.usuario.siga.JavaScriptSigaInterface;
import com.example.usuario.siga.serviceprovider.ServiceProvider;

/**
 * Crawls a hidden Webview for data via javascript injection
 *
 * Created by Julian on 03/03/16.
 */
public class WebCrawlerServiceProvider extends ServiceProvider {
    protected WebView crawlerWebView;

    public WebCrawlerServiceProvider(WebView _crawlerWebView,
                                     ScriptInjectorWebClient injector) {
        crawlerWebView = _crawlerWebView;

        crawlerWebView.setWebViewClient(injector);
    }

    @Override
    public void attemptLogin(JavaScriptSigaInterface sigaJsInterface) {
        Log.d("Crawler", "about to attempt login...");

        crawlerWebView.loadUrl("http://www2.frba.utn.edu.ar/GoTo.php?d=login/index.php");
    }
}
