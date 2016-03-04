package com.example.usuario.siga.serviceprovider.webviewcrawler;

import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.usuario.siga.fileloader.CantLoadFileException;
import com.example.usuario.siga.fileloader.FileLoader;
import com.example.usuario.siga.fileloader.FileLoaderFacade;
import com.example.usuario.siga.fileloader.UninitializedFileLoaderException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Julian on 24/01/16.
 *
 * Injects JavaScript depending on the URL onPageFinished
 */
public class ScriptInjectorWebClient extends WebViewClient{

    private FileLoader files;
    private Map<String, String> jsForUrls;

    public ScriptInjectorWebClient() {
        files = FileLoaderFacade.getFileLoader();

        jsForUrls = new HashMap();
        jsForUrls.put("www.siga.frba.utn.edu.ar", "www/js/crawler/dataExtractor.js");
        jsForUrls.put("www2.frba.utn.edu.ar", "www/js/crawler/login.js");
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        try {
            String hostUrl = new URI(url).getHost();
            String jsFileName = jsForUrls.get(hostUrl);

            injectJs(view, jsFileName);
        } catch (URISyntaxException e){
            e.printStackTrace();
        } catch (UninitializedFileLoaderException e) {
            e.printStackTrace();
        } catch (CantLoadFileException e) {
            e.printStackTrace();
        }
    }

    protected void injectJs(WebView view, String jsFileName) throws CantLoadFileException, UninitializedFileLoaderException {
        Log.d("ScriptInjectorWebClient", "About to inject: " + jsFileName);

        view.loadUrl(
                "javascript:(function(){" +
                    files.load("www/js/onDocumentReady.js") +
                    files.load(jsFileName) +
                "}())");
    }
}
