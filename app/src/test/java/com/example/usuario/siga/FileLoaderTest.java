package com.example.usuario.siga;

import android.test.mock.MockContext;

import com.example.usuario.siga.fileloader.CantLoadFileException;
import com.example.usuario.siga.fileloader.ContextFileLoader;
import com.example.usuario.siga.fileloader.FileLoader;
import com.example.usuario.siga.fileloader.FileLoaderFacade;
import com.example.usuario.siga.fileloader.UninitializedFileLoaderException;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

public class FileLoaderTest {
    @Test
    public void loadJavaScript() throws Exception {
        ContextFileLoader loader = new ContextFileLoader(new MockContext()){
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
        ContextFileLoader loader = new ContextFileLoader(new MockContext()){
            @Override
            protected InputStream fileInputStream(String file) throws IOException{
                throw new IOException();
            }
        };

        loader.load("someBrokenFile.file");
    }

    @Test(expected= UninitializedFileLoaderException.class)
    public void throwsWhenFacadeLoaderUninitialized() throws CantLoadFileException, UninitializedFileLoaderException {
        FileLoader fileLoader = FileLoaderFacade.getFileLoader();

        fileLoader.load("something.file");
    }
}