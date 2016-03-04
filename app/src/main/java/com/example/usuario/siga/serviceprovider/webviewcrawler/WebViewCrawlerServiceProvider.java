package com.example.usuario.siga.serviceprovider.webviewcrawler;

import android.util.Log;
import android.webkit.WebView;

import com.example.usuario.siga.JavaScriptSigaInterface;
import com.example.usuario.siga.serviceprovider.ServiceProvider;

/**
 * Crawls a hidden Webview for data via javascript injection
 *
 * Created by Julian on 03/03/16.
 */
public class WebViewCrawlerServiceProvider extends ServiceProvider {
    private WebView crawlerWebView;

    public WebViewCrawlerServiceProvider(WebView _crawlerWebView) {
        crawlerWebView = _crawlerWebView;

        crawlerWebView.setWebViewClient(new ScriptInjectorWebClient());
    }

    @Override
    public void attemptLogin(JavaScriptSigaInterface sigaJsInterface) {
        Log.d("Crawler", "about to attempt login...");

        crawlerWebView.loadUrl("http://www2.frba.utn.edu.ar/GoTo.php?d=login/index.php");
    }
}
