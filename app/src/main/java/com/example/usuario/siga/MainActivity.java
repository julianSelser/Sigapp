package com.example.usuario.siga;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.example.usuario.siga.service.LoginService;
import com.example.usuario.siga.sigapifactory.AndroidCrawlerSigAPIFactory;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = getPreferences(MODE_PRIVATE);
        WebView crawlerWebView = (WebView) findViewById(R.id.crawler_webview);
        WebView webAppWebView = (WebView) findViewById(R.id.webapp_webview);

        LoginService loginService = /* Your reaction when Im assigning this comment as a variable */

        new AndroidCrawlerSigAPIFactory(crawlerWebView, prefs, getAssets())
            .crawlFakeLocalSiga()
            .makeSigAPI()
            .addItselfAsJsInterfaceTo(webAppWebView)
            .getLoginService()
            .clearPersistentCredentials();

        webAppWebView.loadUrl(loginService.thereIsNoSavedCredentials()
                        ? "file:///android_asset/www/html/webapp/login.html"
                        : "file:///android_asset/www/html/webapp/main.html"
        );
    }
}
