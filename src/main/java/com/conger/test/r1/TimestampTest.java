package com.conger.test.r1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimestampTest {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(new File("/home/jj/tmp/2.txt")));
        String line = null;
        Pattern p = Pattern.compile("([0-9]{1,})");
        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        while ((line = br.readLine()) != null) {
            if (line.contains("timestamp")) {
                Matcher m = p.matcher(line);
                if (m.find()) {
                    String timestamp = m.group(1);
                    calendar.setTimeInMillis(Long.parseLong(timestamp) * 1000);
                    line = line.replace(timestamp, dateFormat.format(calendar.getTime()));
                }
                
            }
            System.out.println(line);
        }
        br.close();
    }
}
