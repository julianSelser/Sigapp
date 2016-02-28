package com.example.usuario.siga;

import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Julian on 24/01/16.
 *
 * Injects JavaScript depending on the URL onPageFinished
 */
public class UrlJsInjectorWebClient extends WebViewClient{

    private FileLoader files;
    private Map<String, String> jsForUrls;

    public UrlJsInjectorWebClient(FileLoader _fileLoader){
        files = _fileLoader;

        jsForUrls = new HashMap();
        jsForUrls.put("www.siga.frba.utn.edu.ar", "dataExtractor.js");
        jsForUrls.put("www2.frba.utn.edu.ar", "login.js");
        jsForUrls.put("javascriptPlayground.html", "testing.js");
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        try {
            injectUrlDependantJs(view, url);
        } catch (CantLoadFileException e) {
            //TODO: decide what to do when javascript cant be read. crash the app?
        }
        catch (URISyntaxException e){
            //TODO: decide what happens when the uri parser breaks? isnt it like the floor dissapearing under your feet?
        }
    }

    private void injectUrlDependantJs(WebView view, String url) throws CantLoadFileException, URISyntaxException {
        String hostUrl = new URI(url).getHost();
        String jsForUrl = jsForUrls.get(hostUrl);

        injectJsInto(view, (null != jsForUrl)? jsForUrl: "unknown.js");
    }

    protected void injectJsInto(WebView view, String jsFileName) throws CantLoadFileException {
        Log.d("UrlJsInjectorWebClient", "About to inject: " + jsFileName);

        view.loadUrl("javascript:(function(){" +
                files.load("onDocumentReady.js") +
                files.load(jsFileName) + "}())");
    }
}
