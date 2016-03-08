package com.example.usuario.siga.service;

/**
 * Interface to provide our app with data
 *
 * Created by Julian on 03/03/16.
 */
public interface Service {

    boolean success();

    boolean failure();

    String getData(); //TODO: return JSON

    void startQuerying();
}
