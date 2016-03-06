package com.example.usuario.siga;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.example.usuario.siga.fileloader.ContextFileLoader;
import com.example.usuario.siga.fileloader.FileLoaderFacade;
import com.example.usuario.siga.serviceprovider.webcrawler.DummyWebCrawlerServiceProvider;
import com.example.usuario.siga.serviceprovider.webcrawler.ScriptInjectorWebClient;

public class MainActivity extends AppCompatActivity {
    private WebView crawlerWebView;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = getPreferences(MODE_PRIVATE);

        FileLoaderFacade.setFileLoader(new ContextFileLoader(this));

        JavaScriptSigaInterface sigaJs = new JavaScriptSigaInterface();
        crawlerWebView = (WebView) findViewById(R.id.crawler_webview);
        WebView webAppWebView = (WebView) findViewById(R.id.webapp_webview);

        new WebViewConfigurer(webAppWebView, sigaJs).configure();
        new WebViewConfigurer(crawlerWebView, sigaJs).configure();

        DummyWebCrawlerServiceProvider provider = new DummyWebCrawlerServiceProvider(crawlerWebView, prefs, new ScriptInjectorWebClient());
        sigaJs.setProvider(provider);

//        provider.shouldFailLoginOnce();
//        provider.clearPersistentCredentials();

        webAppWebView.loadUrl(thereIsNoSavedCredentials()
                ? "file:///android_asset/www/html/webapp/login.html"
                : "file:///android_asset/www/html/webapp/main.html"
        );
//        webAppWebView.loadUrl("file:///android_asset/www/html/javascriptPlayground.html");
    }

    private boolean thereIsNoSavedCredentials() {
        return prefs.getString("cip", "").isEmpty() || prefs.getString("pass", "").isEmpty();
    }
}
