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
 * TODO: cache loaded javascripts to avoid constantly hitting physical files. (maybe not important since we will be updating like once day and we have data to show, so the user wont be waiting)
 */
public class JavaScriptLoader {

    Context context;

    public JavaScriptLoader(Context c){
        context = c;
    }

    public String load(String jsToLoad) throws CantReadJavaScriptException{
        try{
            return urlInjectableJs(jsToLoad);
        }
        catch(IOException e){
            throw new CantReadJavaScriptException();
        }
    }

    private String urlInjectableJs(String jsToLoad) throws IOException{
        return "javascript:(function(){" + readFromFile(jsToLoad) + "}())";
    }

    private String readFromFile(String jsToLoad) throws IOException {
        String line;
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(openJsInputStream(jsToLoad)));

        StringBuilder js = new StringBuilder();

        while((line = reader.readLine()) != null){
            js.append(line);
        }

        reader.close();

        return js.toString();
    }

    protected InputStream openJsInputStream(String jsToLoad) throws IOException{
        return context.getAssets().open(jsToLoad);
    }
}

class CantReadJavaScriptException extends Exception {}