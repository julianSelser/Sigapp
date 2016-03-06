package com.example.usuario.siga.serviceprovider.webcrawler;

import android.content.SharedPreferences;
import android.util.Log;
import android.webkit.WebView;

import com.example.usuario.siga.JavaScriptSigaInterface;
import com.example.usuario.siga.serviceprovider.ServiceProvider;

/**
 * Crawls a hidden Webview for data via javascript injection
 *
 * Created by Julian on 03/03/16.
 */
public class WebCrawlerServiceProvider extends ServiceProvider {
    //TODO: should go elsewhere
    private String loginData; //should be json?
    private boolean loginFailure;
    private boolean isLoggedIn;
    protected SharedPreferences credentialsStore;
    //TODO: end should go elsewhere

    protected WebView crawlerWebView;

    public WebCrawlerServiceProvider(WebView _crawlerWebView, SharedPreferences prefs,
                                     ScriptInjectorWebClient injector) {
        crawlerWebView = _crawlerWebView;
        credentialsStore = prefs;

        crawlerWebView.setWebViewClient(injector);
    }

    @Override
    public void attemptLogin(JavaScriptSigaInterface sigaJsInterface) {
        Log.d("Crawler", "about to attempt login...");

        crawlerWebView.loadUrl("http://www2.frba.utn.edu.ar/GoTo.php?d=login/index.php");
    }

    @Override
    public void logIn(JavaScriptSigaInterface siga) {
        setCipAndPass(siga.getCip(), siga.getPasswd());
        isLoggedIn = true;
    }

    @Override
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    @Override
    public boolean loginFailed() { return loginFailure; }

    @Override
    public String getLoginData() {
        return loginData;
    }

    @Override
    public void loginDataWasWrong(String errors) {
        loginFailure = true;
        loginData = errors;
    }

    protected void setCipAndPass(String cip, String pass){
        SharedPreferences.Editor editor = credentialsStore.edit();

        editor.putString("cip", cip);
        editor.putString("pass", pass);

        editor.commit();
        Log.d("WebCrawlerProvider", "cip and pass should be commited");
    }
}
