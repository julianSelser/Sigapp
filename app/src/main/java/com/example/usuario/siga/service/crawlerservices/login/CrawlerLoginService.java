package com.example.usuario.siga.service.crawlerservices.login;

import android.content.SharedPreferences;
import android.util.Log;
import android.webkit.WebView;

import com.example.usuario.siga.service.crawlerservices.CrawlerService;

/**
 * Crawls a hidden Webview for data via javascript injection
 *
 * Created by Julian on 03/03/16.
 */
public class CrawlerLoginService extends Thread implements CrawlerService {

    private String cip = "";
    private String pass = "";
    private String loginData;
    private boolean loginFailure;
    private boolean isLoggedIn;
    protected SharedPreferences credentialsStore;

    protected WebView crawlerWebView;


    public CrawlerLoginService(WebView _crawlerWebView, SharedPreferences prefs) {
        crawlerWebView = _crawlerWebView;
        credentialsStore = prefs;
    }

    public String getCip(){ return cip; }
    public String getPasswd(){ return pass; }
    public String setCip(String _cip){ return cip = _cip; }
    public String setPasswd(String _pass){ return pass = _pass; }

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
        Log.d("Crawler", "about to attempt login...");

        crawlerWebView.loadUrl("http://www2.frba.utn.edu.ar/GoTo.php?d=login/index.php");
    }

    public void saveCipAndPass(String cip, String pass){
        SharedPreferences.Editor editor = credentialsStore.edit();

        editor.putString("cip", cip);
        editor.putString("pass", pass);

        editor.commit();
        Log.d("WebCrawlerProvider", "cip and pass should be commited");
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
