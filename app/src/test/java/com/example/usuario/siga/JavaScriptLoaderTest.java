package com.example.usuario.siga;

import android.test.mock.MockContext;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

public class JavaScriptLoaderTest {
    @Test
    public void loadJavaScript() throws Exception {
        JavaScriptLoader jsLoader = new JavaScriptLoader(new MockContext()){
            @Override
            protected InputStream openJsInputStream(String jsToLoad) throws IOException{
                String jsCode = "Some js code";

                return new ByteArrayInputStream(jsCode.getBytes("UTF-8"));
            }
        };

        assertEquals(jsLoader.load("someJavaScript.js"), "Some js code");
    }

    @Test(expected=CantReadJavaScriptException.class)
    public void throwsCantReadOnBrokenIO() throws CantReadJavaScriptException{
        JavaScriptLoader jsLoader = new JavaScriptLoader(new MockContext()){
            @Override
            protected InputStream openJsInputStream(String jsToLoad) throws IOException{
                throw new IOException();
            }
        };

        jsLoader.load("someBrokenJavaScript.js");
    }
}