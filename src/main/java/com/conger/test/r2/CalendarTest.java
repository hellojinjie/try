package com.conger.test.r2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarTest
{

    public static void main(String[] args)
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        System.out.println(df.format(c.getTime()));
        System.out.println(c.get(Calendar.DAY_OF_MONTH));
        c.set(Calendar.DAY_OF_MONTH, 1);
        System.out.println(df.format(c.getTime()));
        c.set(Calendar.DAY_OF_MONTH, 0);
        System.out.println(df.format(c.getTime()));
        
    }
}
