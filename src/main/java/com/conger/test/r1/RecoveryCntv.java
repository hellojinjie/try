package com.conger.test.r1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;

import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.UrlEncoded;

import com.google.common.collect.Maps;



public class RecoveryCntv {

    private Map<String, String> games = Maps.newHashMap();
    
    public static void main(String[] args) throws Exception {
        new RecoveryCntv().read();
    }

    public void read() throws Exception {
        
        BufferedReader br = new BufferedReader(new FileReader("/home/jj/tmp/recovery_cntv/merge"));
        
        String line = null;
        while ((line = br.readLine()) != null) {

            MultiMap<String> values = new MultiMap<String>();  
            UrlEncoded.decodeTo(line, values, "UTF-8", 1000);
            System.out.println(values.getString("gameID"));
            System.out.println(values.getString("gameName"));
        }
        
    }

}
