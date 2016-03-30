package com.example.usuario.siga.service.crawler;

import android.support.v4.util.Pair;
import android.webkit.WebView;

import com.example.usuario.siga.fileloader.FileLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Julian on 14/03/16.
 */
public class DummyCrawler extends Crawler {
    public DummyCrawler(WebView crawlerWebView, FileLoader assets) {
        super(crawlerWebView, assets, mappings());
    }

    public static Map<String, Pair<String, String>> mappings(){
        Map<String, Pair<String, String>> mappings = new HashMap();

        mappings.put("siga", Pair.create("file:///android_asset/www/html/crawler/siga.html", "www/js/crawler/dataExtractor.js"));
        mappings.put("login", Pair.create( "file:///android_asset/www/html/crawler/login.html", "www/js/crawler/login.js"));
        mappings.put("failedLogin", Pair.create("file:///android_asset/www/html/crawler/failedLogin.html", "www/js/crawler/login.js"));

        return mappings;
    };
}

