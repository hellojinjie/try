package com.conger.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.Assert;

import org.junit.Test;

public class RegexSpeedTest {

    private static String streamURL = "adaptive://nlds21.neulion.com:443/nlds/nfl/nfltv/as/live/nfltv_hd_pc";
    private static String productID = "nflno_Verizon";

    @Test
    public void testRegexSpeed() {
        long start = System.currentTimeMillis();
        Pattern p = Pattern.compile("/nfl/");
        for (int i = 0; i < 100000; i++) {
            Matcher m = p.matcher(streamURL);
            Assert.assertTrue(m.find());
        }
        System.out.println("Time escaped in regex "
                + (System.currentTimeMillis() - start));
    }

    @Test
    public void testContainsSpeed() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            Assert.assertTrue(streamURL.contains("/nfl/"));
        }
        System.out.println("Time escaped in contains "
                + (System.currentTimeMillis() - start));
    }

    @Test
    public void testStartsWithSpeed() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            Assert.assertTrue(streamURL.startsWith("adaptive"));
        }
        System.out.println("Time escaped in startsWith "
                + (System.currentTimeMillis() - start));
    }
}
