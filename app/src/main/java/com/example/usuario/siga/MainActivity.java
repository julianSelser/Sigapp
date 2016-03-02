package com.example.usuario.siga;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.usuario.siga.fileloader.CantLoadFileException;
import com.example.usuario.siga.fileloader.ContextFileLoader;
import com.example.usuario.siga.fileloader.FileLoader;
import com.example.usuario.siga.fileloader.FileLoaderFacade;
import com.example.usuario.siga.fileloader.UninitializedFileLoaderException;

//TODO:use a dependency injector
//TODO:handle deprecated methods
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FileLoader files = FileLoaderFacade.setFileLoader(new ContextFileLoader(this));
        WebView webView = configureWebView((WebView) findViewById(R.id.activity_main_webview));

//        webView.loadUrl("http://www.siga.frba.utn.edu.ar/");

        try {
            webView.loadDataWithBaseURL("file://androidJavascriptPlayground.html", files.load("androidJavascriptPlayground.html"), "text/html", null, null);
        } catch (CantLoadFileException cantReadFile) {
            cantReadFile.printStackTrace();
        } catch (UninitializedFileLoaderException e) {
            e.printStackTrace();
        }
    }

    //TODO: find a way to put this code in a webview subclass, its killing me
    private WebView configureWebView(WebView webView) {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSavePassword(false);

        webView.addJavascriptInterface(new JavaScriptSiga(), "SIGA");

        webView.setWebViewClient(new ScriptInjectorWebClient());

        return webView;
    }
}
