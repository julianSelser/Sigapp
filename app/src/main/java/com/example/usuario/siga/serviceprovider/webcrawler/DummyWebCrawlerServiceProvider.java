package com.example.usuario.siga.serviceprovider.webcrawler;

import android.content.SharedPreferences;
import android.util.Log;
import android.webkit.WebView;

import com.example.usuario.siga.JavaScriptSigaInterface;

/**
 * Created by Julian on 04/03/16.
 */
public class DummyWebCrawlerServiceProvider extends WebCrawlerServiceProvider {
    private boolean shouldFailLogin;

    public DummyWebCrawlerServiceProvider(WebView _crawlerWebView, SharedPreferences prefs,
                                          ScriptInjectorWebClient injector) {
        super(_crawlerWebView, prefs, injector);

        injector.addJsUrlMap("/android_asset/www/html/crawler/login.html", "www/js/crawler/login.js");
        injector.addJsUrlMap("/android_asset/www/html/crawler/failedLogin.html", "www/js/crawler/login.js");
        injector.addJsUrlMap("/android_asset/www/html/crawler/siga.html", "www/js/crawler/dataExtractor.js");
    }

    @Override
    public void attemptLogin(JavaScriptSigaInterface sigaJsInterface) {

        crawlerWebView.loadUrl(shouldFailLogin
                ?"file:///android_asset/www/html/crawler/failedLogin.html"
                :"file:///android_asset/www/html/crawler/login.html"
        );

        shouldFailLogin = false; //fails once, it's ok for testing
    }

    public void shouldFailLoginOnce(){ shouldFailLogin = true;}

    public void clearPersistentCredentials(){
        Log.d("DummyWebCrawlerProvider", "clearing existing credentials...");

        setCipAndPass(null, null);
    }
}
