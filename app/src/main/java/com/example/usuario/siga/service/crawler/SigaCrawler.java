package com.example.usuario.siga.service.crawler;

import android.support.v4.util.Pair;
import android.webkit.WebView;

import com.example.usuario.siga.fileloader.FileLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Julian on 14/03/16.
 */
public class SigaCrawler extends Crawler {
    public SigaCrawler(WebView crawlerWebView, FileLoader assets) {
        super(crawlerWebView, assets, mappings());
    }

    public static Map<String, Pair<String, String>> mappings(){
        Map<String, Pair<String, String>> mappings = new HashMap();

        mappings.put("siga", Pair.create("http://www.siga.frba.utn.edu.ar/", "www/js/crawler/dataExtractor.js"));
        mappings.put("login", Pair.create("http://www2.frba.utn.edu.ar/GoTo.php?d=login/index.php", "www/js/crawler/login.js"));

        return mappings;
    }
}
