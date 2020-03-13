package edu.frostburg.cosc444;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class JavaApplication {
    static ArrayList<Record> records = new ArrayList<>();
    // Locks for fetching columns
    static ReentrantLock idLock = new ReentrantLock();
    static ReentrantLock districtLock = new ReentrantLock();
    static ReentrantLock neighborhoodLock = new ReentrantLock();
    static ReentrantLock streetLock = new ReentrantLock();
    static ReentrantLock weekdayLock = new ReentrantLock();
    static ReentrantLock monthLock = new ReentrantLock();
    static ReentrantLock partOfDayLock = new ReentrantLock();
    static ReentrantLock victimsLock = new ReentrantLock();

    // Locks for main operations
    static ReentrantLock districtVictims = new ReentrantLock();
    static ReentrantLock partOfDayVictims = new ReentrantLock();
    static ReentrantLock weekdayVictims = new ReentrantLock();
    static ReentrantLock monthVictims = new ReentrantLock();
    static ReentrantLock streetVictims = new ReentrantLock();
    static ReentrantLock neighborhoodVictims = new ReentrantLock();
    static ReentrantLock weeklyPartOfDay = new ReentrantLock();
    static ReentrantLock allRows = new ReentrantLock();

    static String choice = "";

    public static void main(String[] args) {
        String line = "";
        Scanner input = new Scanner(System.in);
        System.out.println("If you choose to print all rows, it will hide the output of the rest of the threads because the data set is so big.\n" +
                "For grading purposes I give you the option to be able to 'toggle' this feature");
        System.out.print("Enter [Y] or [N] to print out all rows: ");
        choice = input.next();
        System.out.println();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("accidents_2017.csv"));

            while ((line = reader.readLine()) != null) {
                line = line.replaceAll("\\s+", "");
                String[] temp = line.split(",");
                Record r = new Record(temp[0],
                        temp[1],
                        temp[2],
                        temp[3],
                        temp[4],
                        temp[5],
                        temp[6],
                        Integer.parseInt(temp[7]));
                records.add(r);
            }
        }catch (Exception e){
            System.out.println(e);
        }

        ColumnReader c0 = new ColumnReader();
        ColumnReader c1 = new ColumnReader();
        ColumnReader c2 = new ColumnReader();
        ColumnReader c3 = new ColumnReader();
        ColumnReader c4 = new ColumnReader();
        ColumnReader c5 = new ColumnReader();
        ColumnReader c6 = new ColumnReader();
        ColumnReader c7 = new ColumnReader();

        ExecutorService executorService = Executors.newFixedThreadPool(8);

        executorService.execute(c0);
        executorService.execute(c1);
        executorService.execute(c2);
        executorService.execute(c3);
        executorService.execute(c4);
        executorService.execute(c5);
        executorService.execute(c6);
        executorService.execute(c7);

        executorService.shutdown();
    }
}
