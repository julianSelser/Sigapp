package com.example.usuario.siga;

import android.test.mock.MockContext;
import android.webkit.WebView;

import com.example.usuario.siga.serviceprovider.webcrawler.ScriptInjectorWebClient;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Julian on 24/01/16.
 */
public class ScriptInjectorWebClientTest {

    private ScriptInjectorWebClientForTesting sigaWebClient;
    private WebView fakeView;

    @Before
    public void setUp() throws Exception {
        fakeView = new WebView(new MockContext());

        sigaWebClient = new ScriptInjectorWebClientForTesting();
    }

    @Test
    public void onPageFinishedLoadsCorrectScripts() throws Exception {
        String[] expectedLoadedjs = new String[]{"www/js/crawler/login.js", "www/js/crawler/dataExtractor.js"};

        sigaWebClient.onPageFinished(fakeView, "http://www2.frba.utn.edu.ar/GoTo.php?d=login/index.php");
        sigaWebClient.onPageFinished(fakeView, "https://www.siga.frba.utn.edu.ar");

        assertArrayEquals(expectedLoadedjs, sigaWebClient.getloadedJs());
    }

    @Test
    public void canAddMappings() throws Exception {
        String[] expectedLoadedjs = new String[]{"some.js"};

        sigaWebClient.addJsUrlMap("www.someurl.com", "some.js");

        sigaWebClient.onPageFinished(fakeView, "http://www.someurl.com");

        assertArrayEquals(expectedLoadedjs, sigaWebClient.getloadedJs());
    }

    @Test
    public void injectsJsForFileUri() throws Exception {
        String[] expectedLoadedjs = new String[]{"some.js"};

        sigaWebClient.addJsUrlMap("/android_asset/www/html/crawler/login.html", "some.js");

        sigaWebClient.onPageFinished(fakeView, "file:///android_asset/www/html/crawler/login.html");

        assertArrayEquals(expectedLoadedjs, sigaWebClient.getloadedJs());
    }

    class ScriptInjectorWebClientForTesting extends ScriptInjectorWebClient {
        private ArrayList<String> jsLoaded = new ArrayList<>();

        public String[] getloadedJs(){
            return jsLoaded.toArray(new String[jsLoaded.size()]);
        }

        @Override
        protected void injectJs(WebView view, String jsFileName){
            jsLoaded.add(jsFileName);
        }
    }
}