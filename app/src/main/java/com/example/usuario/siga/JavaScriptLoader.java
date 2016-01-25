package com.example.usuario.siga;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Julian on 24/01/16.
 *
 * Loads javascript from assets.
 *
 * TODO: validate loaded javascript is in bookmarklet form.
 *
 * TODO: cache loaded javascripts to avoid constantly hitting phisical files. (maybe not important since we will be updating like once day and we have data to show, so the user wont be waiting)
 */
public class JavaScriptLoader {

    Context context;

    public JavaScriptLoader(Context c){
        context = c;
    }

    public String load(String jsToLoad) throws CantReadJavaScriptException{
        BufferedReader reader = null;

        StringBuilder js = new StringBuilder();

        try{
            String red;
            reader = new BufferedReader(
                    new InputStreamReader(openJsInputStream(jsToLoad)));

            while((red = reader.readLine()) != null){
                js.append(red);
            }

            reader.close();
        }
        catch(IOException e){
            throw new CantReadJavaScriptException();
        }

        return js.toString();
    }

    protected InputStream openJsInputStream(String jsToLoad) throws IOException{
        return context.getAssets().open(jsToLoad);
    }
}

class CantReadJavaScriptException extends Exception {}