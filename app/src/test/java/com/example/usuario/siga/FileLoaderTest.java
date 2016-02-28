package com.example.usuario.siga;

import android.test.mock.MockContext;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

public class FileLoaderTest {
    @Test
    public void loadJavaScript() throws Exception {
        FileLoader loader = new FileLoader(new MockContext()){
            @Override
            protected InputStream fileInputStream(String file) throws IOException{
                String contents = "Some file contents";

                return new ByteArrayInputStream(contents.getBytes("UTF-8"));
            }
        };

        assertEquals(loader.load("someFile.file"), "Some file contents");
    }

    @Test(expected=CantLoadFileException.class)
    public void throwsCantReadOnBrokenIO() throws CantLoadFileException {
        FileLoader loader = new FileLoader(new MockContext()){
            @Override
            protected InputStream fileInputStream(String file) throws IOException{
                throw new IOException();
            }
        };

        loader.load("someBrokenFile.file");
    }
}