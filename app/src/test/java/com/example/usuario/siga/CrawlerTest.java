package com.example.usuario.siga;

import android.support.v4.util.Pair;
import android.webkit.WebView;

import com.example.usuario.siga.fileloader.FileLoader;
import com.example.usuario.siga.service.crawler.Crawler;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

/**
 * Created by Julian on 13/03/16.
 */
public class CrawlerTest {

    private Crawler crawler;
    private WebView mockedWebView;

    @Before
    public void setUp(){
        mockedWebView = Mockito.mock(WebView.class);
        FileLoader fileLoader = Mockito.mock(FileLoader.class);
        Map<String, Pair<String, String>> mappings = new HashMap();

        mappings.put("login", Pair.create("someLoginUrl", "somescript.js"));

        crawler = new Crawler(mockedWebView, fileLoader, mappings);
    }

    @Test
    public void canCreateCrawler(){
        assertNotNull(crawler);
    }

    @Test
    public void canCrawlToLogin(){
        crawler.crawlTo("login");

        verify(mockedWebView, atLeastOnce()).loadUrl("someLoginUrl");
    }
}
