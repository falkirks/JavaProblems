package com.falkirks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(50);
        for (int i = 0; i < 50; i++) {
            Runnable worker = new Sender("localhost", 19132, i);
            executor.execute(worker);
        }
        executor.shutdown();
        while (!executor.isTerminated());
        System.out.println("Finished all threads");

    }
}
