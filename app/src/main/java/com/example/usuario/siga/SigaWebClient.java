package com.example.usuario.siga;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Julian on 24/01/16.
 */
public class SigaWebClient extends WebViewClient{

    private JavaScriptLoader jsLoader;
    private Map<String, String> jsForUrls;

    public SigaWebClient(Context ctx){
        jsLoader = new JavaScriptLoader(ctx);

        jsForUrls = new HashMap();
        jsForUrls.put("www.siga.frba.utn.edu.ar", "dataExtractor.js");
        jsForUrls.put("www2.frba.utn.edu.ar", "login.js");
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        try {
            injectUrlDependantJs(view, url);
        } catch (CantReadJavaScriptException e) {
            //TODO: decide what to do when javascript cant be read. crash the app?
        }
        catch (URISyntaxException e){
            //TODO: decide what happens when the uri parser breaks? isnt that like the floor dissapearing under your feet?
        }
    }

    private void injectUrlDependantJs(WebView view, String url) throws CantReadJavaScriptException, URISyntaxException {
        String hostUrl = new URI(url).getHost();

        injectJsInto(view, jsForUrls.get(hostUrl));
    }

    protected void injectJsInto(WebView view, String jsFile) throws CantReadJavaScriptException {
        Log.d("SigaWebClient", "About to inject: " + jsFile);

        view.loadUrl(jsLoader.load(jsFile));
    }
}
