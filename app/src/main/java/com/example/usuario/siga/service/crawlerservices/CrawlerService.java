package com.example.usuario.siga.service.crawlerservices;

import com.example.usuario.siga.service.Service;

/**
 * Created by Julian on 07/03/16.
 */
public interface CrawlerService extends Service {
    //TODO: methods should recieve a JSON

    void succeedWithData(String data);

    void failWithErrors(String errors);
}
