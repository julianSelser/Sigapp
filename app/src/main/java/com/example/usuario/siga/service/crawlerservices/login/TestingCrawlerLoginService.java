package com.example.usuario.siga.service.crawlerservices.login;

import android.content.SharedPreferences;
import android.util.Log;
import android.webkit.WebView;

/**
 *
 *
 * Created by Julian on 04/03/16.
 */
public class TestingCrawlerLoginService extends CrawlerLoginService {
    private boolean shouldFailLogin;

    public TestingCrawlerLoginService(WebView _crawlerWebView, SharedPreferences prefs) {
        super(_crawlerWebView, prefs);
    }

    @Override
    public void startQuerying() {

        crawlerWebView.loadUrl(shouldFailLogin
                ?"file:///android_asset/www/html/crawler/failedLogin.html"
                :"file:///android_asset/www/html/crawler/login.html"
        );

        shouldFailLogin = false; //fails once, it's ok for testing
    }

    public void shouldFailLoginOnce(){ shouldFailLogin = true; }

    public void clearPersistentCredentials(){
        Log.d("DummyWebCrawlerProvider", "clearing existing credentials...");

        saveCipAndPass(null, null);
    }
}
