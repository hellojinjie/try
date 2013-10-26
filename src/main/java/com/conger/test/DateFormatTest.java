package com.conger.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateFormatTest {

    public static void main(String[] args) {
        DateFormatTest test = new DateFormatTest();
        System.out.println(test.parseDate("Fri Mar 08 02:13:40 +0800 2013"));
    }
    
    public Date parseDate(String preDate){
        // Parse the created date

        // Set the format to interpret the activity's created_at data in
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM d HH:mm:ss Z yyyy");
        Date parsedDate;

        try{
            parsedDate = dateFormat.parse(preDate.trim());
        } catch (ParseException e) {
            parsedDate = new Date();
            e.printStackTrace();
        }
        return parsedDate;
    }
    
}
