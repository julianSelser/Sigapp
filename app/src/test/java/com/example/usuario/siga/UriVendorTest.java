package com.example.usuario.siga;

import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

/**
 * Vendor tests to check URI behaviour
 *
 * Created by Julian on 24/01/16.
 */
public class UriVendorTest {
    @Test
    public void getsSigaDomain() throws URISyntaxException {
        URI uri = new URI("http://www.siga.frba.utn.edu.ar/");

        assertEquals(uri.getHost(), "www.siga.frba.utn.edu.ar");
    }

    @Test
    public void getsSiga2Domain() throws URISyntaxException {
        URI uri = new URI("http://www2.frba.utn.edu.ar/GoTo.php?d=login/index.php");

        assertEquals(uri.getHost(), "www2.frba.utn.edu.ar");
    }

    @Test
    public void fileSchemasDontHaveHosts() throws URISyntaxException {
        URI uri = new URI("file:///android_asset/www/html/siga.html");

        assertNull(uri.getHost()); //understandable but annoying
    }

    @Test
    public void getPathIsUsedForFileSchemas() throws URISyntaxException {
        URI uri = new URI("file:///android_asset/www/html/siga.html");

        assertEquals(uri.getPath(), "/android_asset/www/html/siga.html");
    }

}
