package com.example.usuario.siga.fileloader;

/**
 * Abstract FileLoader for loading files
 *
 * Created by Julian on 28/02/16.
 */
public abstract class FileLoader {
    public abstract String load(String fileName) throws CantLoadFileException, UninitializedFileLoaderException;
}
