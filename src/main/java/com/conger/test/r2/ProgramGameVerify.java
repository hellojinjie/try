package com.conger.test.r2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class ProgramGameVerify {

    private static final Logger log = Logger.getLogger(ProgramGameVerify.class);

    private static final String gameCSV = "/home/jj/Downloads/Skype/csv/game.txt";
    private static final String programCSV = "/home/jj/Downloads/Skype/csv/program.txt";
    private static final String gameColumnsTxt = "/home/jj/Downloads/Skype/csv/game_columns.txt";
    private static final String programColumnsTxt = "/home/jj/Downloads/Skype/csv/program_columns.txt";

    private List<String> gameColumns;
    private List<String> programColumns;

    @Before
    public void setup() {
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(new File(gameColumnsTxt)));
            String header = fileReader.readLine();
            gameColumns = Arrays.asList(header.toLowerCase().split("\\ +"));
            fileReader.close();
            fileReader = new BufferedReader(new FileReader(new File(programColumnsTxt)));
            header = fileReader.readLine();
            programColumns = Arrays.asList(header.split("\\ +"));
            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info(gameColumns.toString());
        log.info(programColumns.toString());

        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            Calendar calendar = Calendar.getInstance();
            BufferedReader fileReader = new BufferedReader(new FileReader(new File(gameCSV)));
            CSVParser parser = CSVFormat.TDF.withDelimiter('\t').withQuoteChar('"')
                    .withHeader(gameColumns.toArray(new String[0])).parse(fileReader);
            
            FileWriter writer = new FileWriter(new File("/home/jj/game.mysql"));

            Iterator<CSVRecord> records = parser.iterator();
            records.next();
            while (records.hasNext()) {
                CSVRecord record = records.next();
                writer.write("insert into game values(0" + record.get("id") + ",0" + record.get("game_id") + ",0" + record.get("program_id") + ", '" + record.get("game_date") + "');");
                writer.write("\n");
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            Calendar calendar = Calendar.getInstance();
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(new File(programCSV))));
            CSVParser parser = CSVFormat.TDF.withDelimiter('\t').withQuoteChar('^')
                    .withHeader(programColumns.toArray(new String[0])).parse(fileReader);
            Iterator<CSVRecord> records = parser.iterator();
            FileWriter writer = new FileWriter(new File("/home/jj/program.mysql"));
            records.next();
            while (records.hasNext()) {
                CSVRecord record = null;
                try {
                    record = records.next();
                    writer.write("insert into program values (0" + record.get("program_id") + ",0" + record.get("game_id") + ",'" + record.get("prog_date") + "','" + record.get("path") + "');");
                    writer.write("\n");
                    System.out.println(record.get("program_id") + "\tprog_date:" + record.get("prog_date") + "\tbegin_date:" 
                            + record.get("begin_date") + "\tupdatetime:" + record.get("updatetime") + "\tbegin_date_time:" 
                            + record.get("begin_date_time") + "\tend_date_time:" + record.get("end_date_time"));
                } catch (Exception e) {
                    e.getMessage();
                    continue;
                }
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void verify() {
        synchronized (this) {
            
        }
    }
}
