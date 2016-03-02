package com.example.usuario.siga;

import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.usuario.siga.fileloader.CantLoadFileException;
import com.example.usuario.siga.fileloader.ContextFileLoader;
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
        jsForUrls.put("www.siga.frba.utn.edu.ar", "dataExtractor.js");
        jsForUrls.put("www2.frba.utn.edu.ar", "login.js");
        jsForUrls.put("androidJavascriptPlayground.html", "testing.js");
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        try {
            String hostUrl = new URI(url).getHost();
            String jsFileName = jsForUrls.get(hostUrl);

            injectJsInto(view, (null != jsFileName)? jsFileName: "unknown.js");
        } catch (URISyntaxException e){
            //TODO: decide what happens when the uri parser breaks? isnt it like the floor dissapearing under your feet?
        }
    }

    protected void injectJsInto(WebView view, String jsFileName) {
        Log.d("ScriptInjectorWebClient", "About to inject: " + jsFileName);

        view.loadUrl(injectableJs(jsFileName));
    }

    private String injectableJs(String jsFileName) {
        try {
            String actualJsToInject = files.load(jsFileName);

            if(jsFileName == "testing.js"){
                for(String script: jsForUrls.values()){
                    actualJsToInject += files.load(script) + ";";
                }
            }

            return "javascript:(function(){" +
                    files.load("onDocumentReady.js") +
                    actualJsToInject + "}())";

        } catch (UninitializedFileLoaderException e) {
            e.printStackTrace();
        } catch (CantLoadFileException e) {
            e.printStackTrace();
        }

        throw new RuntimeException("Cant build javascript to inject");
    }
}
