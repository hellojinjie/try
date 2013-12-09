package com.conger.test.r1;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DownloadTest {

    private int downloadRetry = 3;

    public static void main(String[] args) throws Exception {
        new DownloadTest().start();
    }
    
    private void start() throws Exception {
        Thread thread = new Thread(new DownloadWorker(
                "http://127.0.0.1:8000/cntv-receiver.log.2013-08-20-19",
                "/tmp/qostest.log"));

        thread.start();
        thread.join();
    }

    private class DownloadWorker implements Runnable {
        private String url;
        private String filename;

        public DownloadWorker(String url, String filename) {
            this.url = url;
            this.filename = filename;
        }

        public void run() {
            URL downloadURL = null;
            try {
                downloadURL = new URL(url);
            } catch (MalformedURLException e) {
                return;
            }
            InputStream in = null;
            try {
                in = downloadURL.openStream();
            } catch (IOException e) {
                return;
            }
            File downloadFile = new File(filename);
            int tryCount = 1;
            boolean downloadSuccess = false;
            while (tryCount++ <= downloadRetry) {
                if (downloadFile.exists()) {
                    downloadFile.delete();
                }
                try {
                    BufferedOutputStream bs = new BufferedOutputStream(
                            new FileOutputStream(downloadFile));
                    byte[] buffer = new byte[1024 * 100];
                    int count = 0;
                    while ((count = in.read(buffer)) != -1) {
                        bs.write(buffer, 0, count);
                    }
                    bs.close();
                    downloadSuccess = true;
                    break;
                } catch (IOException e) {
                    /* shall retry download? YES */
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e1) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
            if (!downloadSuccess || !downloadFile.exists()) {
                if (downloadFile.exists()) {
                    downloadFile.delete();
                }
                return;
            }
            try {
                in.close();
            } catch (IOException e) {
            }
            
            Reader reader = null;
            try {
                reader = new InputStreamReader(new FileInputStream(downloadFile), "UTF8");
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } finally {
                if (reader == null) {
                    if (downloadFile.exists()) {
                        downloadFile.delete();
                    }
                    return;
                }
            }
            BufferedReader br = new BufferedReader(reader);
            String line = null;
            try {
                while ((line = br.readLine()) != null) {
                   // System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
