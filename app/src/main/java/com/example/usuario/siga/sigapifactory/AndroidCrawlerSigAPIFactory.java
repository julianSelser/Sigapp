package com.example.usuario.siga.sigapifactory;

import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.webkit.WebView;

import com.example.usuario.siga.fileloader.AssetsManagerFileLoader;
import com.example.usuario.siga.fileloader.FileLoader;
import com.example.usuario.siga.service.crawler.Crawler;
import com.example.usuario.siga.service.crawler.DummyCrawler;
import com.example.usuario.siga.service.crawler.SigaCrawler;
import com.example.usuario.siga.service.crawlerservices.login.CrawlerLoginService;
import com.example.usuario.siga.sigapi.AndroidCrawlerSigAPI;

/**
 * Created by Julian on 11/03/16.
 */

public class AndroidCrawlerSigAPIFactory extends SigAPIFactory {

    private Crawler crawler;
    private WebView crawlerWebView;
    private FileLoader fileLoader;
    private SharedPreferences credentialStore;

    public AndroidCrawlerSigAPIFactory(WebView crawlerWebView, SharedPreferences credentialStore,
                                       AssetManager assets) {
        this.crawlerWebView = crawlerWebView;
        this.credentialStore = credentialStore;
        this.fileLoader = new AssetsManagerFileLoader(assets);

        crawlRealSiga();
    }

    @Override
    public AndroidCrawlerSigAPI makeSigAPI() {
        CrawlerLoginService loginService = new CrawlerLoginService(crawler, credentialStore);
        AndroidCrawlerSigAPI sigApi = new AndroidCrawlerSigAPI(loginService);

        sigApi.addItselfAsJsInterfaceTo(crawlerWebView);

        return sigApi;
    }

    public AndroidCrawlerSigAPIFactory crawlFakeLocalSiga(){
        crawler = new DummyCrawler(crawlerWebView, fileLoader);
        
        return this;
    }

    public AndroidCrawlerSigAPIFactory crawlRealSiga(){
        crawler = new SigaCrawler(crawlerWebView, fileLoader);

        return this;
    }
}
