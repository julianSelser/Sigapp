package com.example.usuario.siga;

import android.test.mock.MockContext;
import android.webkit.WebView;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Julian on 24/01/16.
 */
public class SigaWebClientTest {

    private ScriptInjectorWebClientForTesting sigaWebClient;
    private WebView fakeView;

    @Before
    public void setUp() throws Exception {
        fakeView = new WebView(new MockContext());

        sigaWebClient = new ScriptInjectorWebClientForTesting();
    }

    @Test
    public void loadsUnknownScriptForUnknownPages() throws Exception {
        sigaWebClient.onPageFinished(fakeView, "http://someRandomPageThatDoesntNeedScripts.com");

        assertArrayEquals(new String[]{"unknown.js"}, sigaWebClient.getloadedJs());
    }

    @Test
    public void onPageFinishedLoadsCorrectScripts() throws Exception {
        String[] expectedLoadedjs = new String[]{"login.js", "dataExtractor.js"};

        sigaWebClient.onPageFinished(fakeView, "http://www2.frba.utn.edu.ar/GoTo.php?d=login/index.php");
        sigaWebClient.onPageFinished(fakeView, "https://www.siga.frba.utn.edu.ar");

        assertArrayEquals(expectedLoadedjs, sigaWebClient.getloadedJs());
    }

    @Test
    public void whenLoadingTestingHTMLConcatAllScripts() {
        String[] expectedLoadedjs = new String[]{"testing.js"};

        sigaWebClient.onPageFinished(fakeView, "file://androidJavascriptPlayground.html");

        assertArrayEquals(expectedLoadedjs, sigaWebClient.getloadedJs());
    }

    class ScriptInjectorWebClientForTesting extends ScriptInjectorWebClient {
        private ArrayList<String> jsLoaded = new ArrayList<>();

        public String[] getloadedJs(){
            return jsLoaded.toArray(new String[jsLoaded.size()]);
        }

        @Override
        protected void injectJsInto(WebView view, String jsFileName){
            jsLoaded.add(jsFileName);
        }
    }
}