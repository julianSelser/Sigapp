package com.example.usuario.siga;

import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.usuario.siga.sigapi.AndroidCrawlerSigAPI;
import com.example.usuario.siga.sigapifactory.AndroidCrawlerSigAPIFactory;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertNotNull;


/**
 * Created by Julian on 12/03/16.
 */
public class SigaApiTest {
    WebView crawlerWebView;
    WebSettings mockedSettings;
    SharedPreferences credentialStore;

    AndroidCrawlerSigAPI siga;
    AndroidCrawlerSigAPIFactory factory;
    AssetManager assets;

    @Before
    public void testApiCreation(){

        mockedSettings = Mockito.mock(WebSettings.class);
        crawlerWebView = Mockito.mock(WebView.class);
        credentialStore = Mockito.mock(SharedPreferences.class);
        assets = Mockito.mock(AssetManager.class);

        factory = new AndroidCrawlerSigAPIFactory(crawlerWebView, credentialStore, assets);

        Mockito.when(crawlerWebView.getSettings()).thenReturn(mockedSettings);

        siga = factory.makeSigAPI();
    }

    @Test
    public void testApiWasCreated(){
        assertNotNull(siga);
    }

    @Test
    public void testGetLoginService(){
        assertNotNull(siga.getLoginService());
    }

    @Test
    public void testSigApiAddsItselfIntoCrawler(){
        AndroidCrawlerSigAPI crawlerSigApi = factory.makeSigAPI();

        Mockito.verify(mockedSettings, Mockito.atLeastOnce()).setJavaScriptEnabled(true);
        Mockito.verify(mockedSettings, Mockito.atLeastOnce()).setSavePassword(false);
        Mockito.verify(crawlerWebView, Mockito.atLeastOnce())
                .addJavascriptInterface(crawlerSigApi, "SigAPI");
    }

    @Test
    public void testSigApiAddsItselfIntoArbitraryWebViews(){
        WebView arbitraryWebView = Mockito.mock(WebView.class);

        Mockito.when(arbitraryWebView.getSettings()).thenReturn(mockedSettings);

        siga.addItselfAsJsInterfaceTo(arbitraryWebView);

        Mockito.verify(arbitraryWebView, Mockito.atLeastOnce())
                .addJavascriptInterface(siga, "SigAPI");
    }
}
