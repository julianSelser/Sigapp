package com.example.usuario.siga;

import android.content.Context;
import android.test.mock.MockContext;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Julian on 24/01/16.
 */
public class SigaWebClientTest {

    private SigaWebClientForTesting sigaWebClient;
    private WebView fakeView;

    @Before
    public void setUp() throws Exception {
        Context ctx = new MockContext();

        fakeView = new WebView(new MockContext());

        sigaWebClient = new SigaWebClientForTesting(ctx);
    }

    @Test
    public void onPageFinishedLoadsCorrectScripts() throws Exception {
        String[] expectedLoadedjs = new String[]{"login.js", "dataExtractor.js"};

        sigaWebClient.onPageFinished(fakeView, "http://www2.frba.utn.edu.ar/GoTo.php?d=login/index.php");
        sigaWebClient.onPageFinished(fakeView, "https://www.siga.frba.utn.edu.ar");

        assertArrayEquals(expectedLoadedjs, sigaWebClient.getloadedJs());
    }

    class SigaWebClientForTesting extends SigaWebClient{
        private ArrayList<String> jsLoaded = new ArrayList<>();

        public SigaWebClientForTesting(Context ctx) { super(ctx); }

        public String[] getloadedJs(){
            return jsLoaded.toArray(new String[jsLoaded.size()]);
        }

        @Override
        protected void injectJsInto(WebView view, String jsToLoad){
            jsLoaded.add(jsToLoad);
        }
    }
}