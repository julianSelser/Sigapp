package com.example.usuario.siga;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.example.usuario.siga.fileloader.ContextFileLoader;
import com.example.usuario.siga.fileloader.FileLoaderFacade;
import com.example.usuario.siga.jsservicehub.JavaScriptSigaInterface;
import com.example.usuario.siga.service.crawlerservices.login.TestingCrawlerLoginService;
import com.example.usuario.siga.service.crawlerservices.ScriptInjectorWebClient;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = getPreferences(MODE_PRIVATE);


        JavaScriptSigaInterface sigaJs = new JavaScriptSigaInterface();
        WebView crawlerWebView = new WebViewConfigurer((WebView) findViewById(R.id.crawler_webview), sigaJs).configure();
        WebView webAppWebView = new WebViewConfigurer((WebView) findViewById(R.id.webapp_webview), sigaJs).configure();


        ScriptInjectorWebClient injector = new ScriptInjectorWebClient(new ContextFileLoader(this), urlToJsMappings());
        crawlerWebView.setWebViewClient(injector);

        TestingCrawlerLoginService provider = new TestingCrawlerLoginService(crawlerWebView, prefs);
        sigaJs.setCrawler(crawlerWebView);
        sigaJs.setLoginProvider(provider);

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

    private Map<String, String> urlToJsMappings(){

        Map<String, String> jsForUrls = new HashMap();

//        jsForUrls.put("www.siga.frba.utn.edu.ar", "www/js/crawler/dataExtractor.js");
//        jsForUrls.put("www2.frba.utn.edu.ar", "www/js/crawler/login.js");
//        jsForUrls.put(null, "www/js/unknown.js");

        jsForUrls.put("/android_asset/www/html/crawler/login.html", "www/js/crawler/login.js");
        jsForUrls.put("/android_asset/www/html/crawler/failedLogin.html", "www/js/crawler/login.js");
        jsForUrls.put("/android_asset/www/html/crawler/siga.html", "www/js/crawler/dataExtractor.js");

        return jsForUrls;


    }
}
