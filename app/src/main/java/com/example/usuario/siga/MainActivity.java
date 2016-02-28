package com.example.usuario.siga;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

//TODO:use a dependency injector
//TODO:handle deprecated methods
public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private FileLoader files = new FileLoader(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureWebView();

        //webView.loadUrl("http://www.siga.frba.utn.edu.ar/");

        try {
            webView.loadData(files.load("javascriptPlayground.html"), "text/html", null);
        } catch (CantLoadFileException cantReadFile) {
            cantReadFile.printStackTrace();
        }
    }

    private void configureWebView(){
        webView = (WebView) findViewById(R.id.activity_main_webview);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSavePassword(false);

        webView.addJavascriptInterface(new JavaScriptSiga(), "SIGA");

        webView.setWebViewClient(new UrlJsInjectorWebClient(files));
    }
}
