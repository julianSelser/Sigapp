package com.example.usuario.siga;

import android.content.res.AssetManager;

import com.example.usuario.siga.fileloader.CantLoadFileException;
import com.example.usuario.siga.fileloader.AssetsManagerFileLoader;
import com.example.usuario.siga.fileloader.FileLoader;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

public class FileLoaderTest {
    @Test
    public void loadJavaScript() throws Exception {
        AssetsManagerFileLoader loader = new AssetsManagerFileLoader(Mockito.mock(AssetManager.class)){
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
        AssetsManagerFileLoader loader = new AssetsManagerFileLoader(Mockito.mock(AssetManager.class)){
            @Override
            protected InputStream fileInputStream(String file) throws IOException{
                throw new IOException();
            }
        };

        loader.load("someBrokenFile.file");
    }
}