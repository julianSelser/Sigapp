package com.example.usuario.siga.service.crawler;

import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.usuario.siga.fileloader.CantLoadFileException;
import com.example.usuario.siga.fileloader.FileLoader;

import java.util.Map;

/**
 * Created by Julian on 24/01/16.
 *
 * Injects JavaScript depending on the URL onPageFinished
 */
public class ScriptInjectorWebClient extends WebViewClient{

    private FileLoader files;
    protected Map<String, String> jsForUrls;

    public ScriptInjectorWebClient(FileLoader files, Map<String, String> jsForUrls) {
        this.jsForUrls = jsForUrls;
        this.files = files;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        try {
            injectJs(view, jsForUrls.get(url));
        } catch (CantLoadFileException e) {
            e.printStackTrace();
        }
    }

    protected void injectJs(WebView view, String jsFileName) throws CantLoadFileException {
        Log.d("ScriptInjectorWebClient", "About to inject: " + jsFileName);

        view.loadUrl(
                "javascript:(function(){" +
                    files.load("www/js/onDocumentReady.js") +
                    files.load(jsFileName) +
                "}())");
    }
}
