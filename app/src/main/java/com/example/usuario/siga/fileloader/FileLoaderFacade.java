package com.example.usuario.siga.fileloader;

/**
 * Facade to allow loading files easier and globally
 * It's not complete dogpoop because we delegate the loading to an instance we can test
 * (hint: it IS complete dogpoop)
 *
 * Created by Julian on 28/02/16.
 */
public class FileLoaderFacade{
    private static FileLoader fileLoader = new FileLoader() {
        @Override
        public String load(String fileName) throws UninitializedFileLoaderException {
            throw new UninitializedFileLoaderException();
        }
    };

    private FileLoaderFacade() {}

    public static String load(String fileName) throws CantLoadFileException, UninitializedFileLoaderException {
        return fileLoader.load(fileName);
    }

    public static FileLoader setFileLoader(FileLoader _fileLoader) {
        fileLoader = _fileLoader;

        return fileLoader;
    }

    public static FileLoader getFileLoader() {
        return fileLoader;
    }
}
