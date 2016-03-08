package com.example.usuario.siga.service.crawlerservices;

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

    public ScriptInjectorWebClient(FileLoader _files, Map<String, String> _jsForUrls) {
        jsForUrls = _jsForUrls;
        files = _files;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        try {
            URI uri = new URI(url);
            String key = uri.getScheme().equals("file")? uri.getPath() : uri.getHost();
            String jsFileName = jsForUrls.get(key);

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
