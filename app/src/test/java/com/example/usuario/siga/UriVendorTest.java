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
    public void getsTestingHTML() throws URISyntaxException {
        URI uri = new URI("file://javascriptPlayground.html");

        assertEquals(uri.getHost(), "javascriptPlayground.html");
    }

    @Test
    public void getsOtherTestingHTML() throws URISyntaxException {
        URI uri = new URI("http://www.androidJavascriptPlayground.com");

        assertEquals(uri.getHost(), "www.androidJavascriptPlayground.com");
    }
}
