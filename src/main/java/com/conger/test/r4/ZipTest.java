package com.conger.test.r4;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.zookeeper.common.IOUtils;

public class ZipTest
{

    public static void main(String args[]) throws Exception
    {
        File baseDir = new File("/tmp");
        ZipFile zf = new ZipFile(new File(
                "/home/jj/tmp/User_And_Game_Usage_Report_20140526-20140601_cntvsports.zip"));
        Enumeration<? extends ZipEntry> entries = zf.entries();
        while (entries.hasMoreElements())
        {
            ZipEntry entry = entries.nextElement();
            System.out.println(entry.getName());
            if (entry.isDirectory())
            {
                File dir = new File(baseDir, entry.getName());
                if (dir.exists() == false)
                {
                    dir.mkdirs();
                }
            }
            else
            {
                File file = new File(baseDir, entry.getName());
                if (file.exists())
                {
                    continue;
                }
                File parentFile = file.getParentFile();
                if (parentFile.exists() == false)
                {
                    parentFile.mkdirs();
                }
                InputStream is = zf.getInputStream(entry);
                FileOutputStream os = new FileOutputStream(new File(baseDir, entry.getName()));
                IOUtils.copyBytes(is, os, 10240);
                is.close();
                os.close();
            }
        }
        zf.close();
    }
}
