package com.javaxpert.books.devjavapro.benchmark;


/**
 * Manual try to create a benchmark in Java
 * @author deadbrain - jerome@javaxpert.com
 */
public class HandCraftedBenchmark {
    private static final int MAX_LOOPS = 100;
    private static final int OBJECTS_TO_CREATE = 1000;

    private  void createObject(){
        new Object();
    }
    public static void main(String[] args) {
        HandCraftedBenchmark bench = new HandCraftedBenchmark();
        long start = System.currentTimeMillis();

        for(int i = 0;i < MAX_LOOPS;i++){
            for(int j=0;j<OBJECTS_TO_CREATE;j++) {
                bench.createObject();
            }
        }

        long stop = System.currentTimeMillis();
        long durationMs = (stop -start);
        System.out.println("Elasped time ="  + durationMs + " ms");
        System.out.println("Avrage time "  + (durationMs/MAX_LOOPS));
    }
}

