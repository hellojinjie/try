package com.conger.test.r1;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarTest {

    public static void main(String[] args) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, 5);
        calendar.set(Calendar.DAY_OF_MONTH, 11);
        for (int i = 0; i < 200; i++) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            String date = dateFormat.format(calendar.getTime());
            System.out.println("alter table qos_log add partition (dt='" + date 
                    + "', category='mobile') location '/user/qos/logs/mobile_log/dt=" + date + "';");
        }
    }
}
