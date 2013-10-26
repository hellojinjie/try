package com.conger.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;

public class HadoopFileSystemTest {

    private static final String sql = "alter table qos_log add partition (dt='{dt}', category='{category}') location '/user/qos/logs/pc_log/site={category}/dt={dt}';";
    
    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "hdfs://oak.cloud.neulion.com.cn:8020");
        // for hadoop 1.x
        configuration.set("fs.default.name", "hdfs://oak.cloud.neulion.com.cn:8020");
        FileSystem fileSystem = FileSystem.get(configuration);
        RemoteIterator<LocatedFileStatus> iter = fileSystem.listFiles(new Path("/user/qos/logs/pc_log"), true);
        while (iter.hasNext()) {
            LocatedFileStatus status = iter.next();
            String path = status.getPath().toUri().getPath();
            String result = sql.replace("{category}", getSite(path));
            result = result.replace("{dt}", getDt(path));
            System.out.println(result);
        }
        
    }

    private static String getSite(String path) {
        Pattern p = Pattern.compile(".+site=([^/]+)/");
        Matcher matcher = p.matcher(path);
        matcher.find();
        return matcher.group(1);
    }
    
    private static String getDt(String path) {
        Pattern p = Pattern.compile(".+dt=(.*)/");
        Matcher matcher = p.matcher(path);
        matcher.find();
        return matcher.group(1);
    }
}
