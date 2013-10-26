package com.conger.test;

import java.io.InputStream;
import java.net.URL;

import sun.net.www.protocol.http.HttpURLConnection;

public class URLConnectionTest {
    
    public static void main(String[] args) throws Exception {
        
        new URLConnectionTest().openOneURL();
    }
    
    private void openOneURL() throws Exception {
        URL url = new URL("http://www.google.com.hk");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        InputStream input = connection.getInputStream();
        connection.disconnect();
    }

}
