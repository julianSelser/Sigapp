package com.example.usuario.siga;

import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.usuario.siga.jsservicehub.JavaScriptSigaInterface;

/**
 * Builds WebView
 *
 * Created by Julian on 03/03/16.
 */
public class WebViewConfigurer {
    WebView webView;
    JavaScriptSigaInterface sigaJs;

    public WebViewConfigurer(WebView _webview, JavaScriptSigaInterface _sigaJs){
        webView = _webview;
        sigaJs = _sigaJs;
    }

    public WebView configure() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSavePassword(false);

        webView.addJavascriptInterface(sigaJs, "SIGA");

        return webView;
    }
}
