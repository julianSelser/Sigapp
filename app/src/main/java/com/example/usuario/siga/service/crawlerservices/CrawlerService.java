package com.example.usuario.siga.service.crawlerservices;

import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.example.usuario.siga.service.Service;

/**
 * Created by Julian on 07/03/16.
 */
public interface CrawlerService extends Service {

    //TODO: methods should recieve a JSON
    @JavascriptInterface
    void succeedWithData(String data);

    @JavascriptInterface
    void failWithErrors(String errors);
}
