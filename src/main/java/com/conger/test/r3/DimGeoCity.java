package com.conger.test.r3;

import java.io.EOFException;
import java.io.File;
import java.io.RandomAccessFile;

public class DimGeoCity
{

    public static void main(String[] args) throws Exception
    {
        long offset = 7521396l;
        File file = new File("GeoIPCity.dat");
        RandomAccessFile db = new RandomAccessFile(file, "r");
        db.seek(offset * 5);
        byte[] buffer = null;
        try
        {
            buffer = new byte[60];
            db.readFully(buffer);
            int record_buf_offset = 1;
            // get region
            int str_length = 0;
            while (buffer[record_buf_offset + str_length] != '\0')
                str_length++;
            if (str_length > 0) {
                System.out.print(new String(buffer, record_buf_offset, str_length));
            }
            record_buf_offset += str_length + 1;
            str_length = 0;

            // get city
            while (buffer[record_buf_offset + str_length] != '\0')
                str_length++;
            if (str_length > 0) {
                System.out.println(new String(buffer, record_buf_offset, str_length, "ISO-8859-1"));
            }
        }
        catch (EOFException e)
        {
            System.out.println("end of file");
        }
        db.close();
    }
}
