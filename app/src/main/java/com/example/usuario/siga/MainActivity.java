package com.example.usuario.siga;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.example.usuario.siga.fileloader.ContextFileLoader;
import com.example.usuario.siga.fileloader.FileLoaderFacade;
import com.example.usuario.siga.serviceprovider.webcrawler.WebCrawlerServiceProvider;

public class MainActivity extends AppCompatActivity {
    private WebView crawlerWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FileLoaderFacade.setFileLoader(new ContextFileLoader(this));

        JavaScriptSigaInterface sigaJs = new JavaScriptSigaInterface();
        crawlerWebView = (WebView) findViewById(R.id.crawler_webview);
        WebView webappWebView = (WebView) findViewById(R.id.webapp_webview);

        new WebViewConfigurer(webappWebView, sigaJs).configure();
        new WebViewConfigurer(crawlerWebView, sigaJs).configure();

        sigaJs.setProvider(new WebCrawlerServiceProvider(crawlerWebView));

        webappWebView.loadUrl("file:///android_asset/www/html/login.html");
    }
}
