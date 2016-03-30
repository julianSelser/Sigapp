package com.example.usuario.siga.service.crawlerservices.login;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.usuario.siga.service.LoginService;
import com.example.usuario.siga.service.crawler.Crawler;
import com.example.usuario.siga.service.crawlerservices.CrawlerService;

/**
 * Crawls a hidden Webview for data via javascript injection
 *
 * Created by Julian on 03/03/16.
 */
public class CrawlerLoginService extends LoginService implements CrawlerService {

    private Crawler crawler;
    private String loginData;
    private boolean loginFailure;
    private boolean isLoggedIn;

    public CrawlerLoginService(Crawler crawler, SharedPreferences credentialsStore) {
        super(credentialsStore);
        this.crawler = crawler;
    }

    @Override
    public boolean success() {
        return isLoggedIn;
    }

    @Override
    public boolean failure() {
        return loginFailure;
    }

    @Override
    public String getData() {
        return loginData;
    }

    @Override
    public void startQuerying() {
        crawler.crawlTo("login"); Log.d("LoginCrawler", "about to attempt login...");
    }

    @Override
    public void succeedWithData(String data) {
        isLoggedIn = true;
        saveCipAndPass(cip, pass);
    }

    @Override
    public void failWithErrors(String errors) {
        loginFailure = true;
        loginData = errors;
    }
}
