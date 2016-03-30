package com.example.usuario.siga.fileloader;

import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Julian on 24/01/16.
 *
 * Load files from assets
 *
 * TODO: cache loaded javascripts to avoid constantly hitting physical files
 */
public class AssetsManagerFileLoader extends FileLoader{

    AssetManager assets;

    public AssetsManagerFileLoader(AssetManager assets){
        this.assets = assets;
    }

    public String load(String file) throws CantLoadFileException {
        try{
            return readFromFile(file);
        }
        catch(IOException e){
            throw new CantLoadFileException();
        }
    }

    private String readFromFile(String file) throws IOException {
        String line;
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(fileInputStream(file)));

        StringBuilder fileString = new StringBuilder();

        while((line = reader.readLine()) != null){
            fileString.append(line);
        }

        reader.close();

        return fileString.toString();
    }

    protected InputStream fileInputStream(String file) throws IOException{
        return assets.open(file);
    }
}