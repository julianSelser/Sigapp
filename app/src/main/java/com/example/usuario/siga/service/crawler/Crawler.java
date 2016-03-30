package com.example.usuario.siga.service.crawler;


import android.support.v4.util.Pair;
import android.webkit.WebView;

import com.example.usuario.siga.fileloader.FileLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * Allows crawling URLs defined in mappings defined in subclasses. Hands said mappings to injector.
 *
 * Created by Julian on 13/03/16.
 */
public class Crawler {
    WebView crawlerWebView;
    Map<String, Pair<String, String>> mappings;

    public Crawler(WebView crawlerWebView, FileLoader assets, Map<String, Pair<String, String>> mappings){
        this.mappings = mappings;
        this.crawlerWebView = crawlerWebView;

        crawlerWebView.setWebViewClient(new ScriptInjectorWebClient(assets, urlTojsMappings()));
    }

    public void crawlTo(String somewhere) {
        if(!mappings.containsKey(somewhere)){
            throw new RuntimeException("No URL defined for crawling");
        }

        crawlerWebView.loadUrl(mappings.get(somewhere).first);
    }

    private Map<String, String> urlTojsMappings(){
        Map<String, String> urlToJsMappings = new HashMap();

        for(Map.Entry<String, Pair<String, String>> entry : mappings.entrySet()){
            Pair<String, String> urlJsPair = entry.getValue();

            urlToJsMappings.put(urlJsPair.first, urlJsPair.second);
        }

        return urlToJsMappings;
    }
}
