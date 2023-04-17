package com.javaxpert.books.devjavapro.benchmark;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * show how to do a reduction over a list
 * in a single threaded or multi  threaded environment
 * @author deadbrain
 */
public class ComputeReduction {
    private static final int MAX_SIZE = 100000;
    private List<Integer> dummyList;

    public ComputeReduction(){
        dummyList=new ArrayList(MAX_SIZE);
        for(int i=0;i<MAX_SIZE;i++){
            dummyList.add(i);
        }
    }

    public void computeReductionSingleThreaded(){
        long start = System.currentTimeMillis();
        for(int i =0;i<5;i++){
            int sum = dummyList.stream().reduce((integer, integer2) ->  integer+integer2).get();
            System.out.println("Sum computed = "+ sum + "  iteratiion = " + i);
        }
        long stop = System.currentTimeMillis();
        System.out.println("Computation time  : " + (stop -start) + " ms");
    }

    public void computeReductionWithExecService()  {
        Runnable r = () -> {
            double start = System.currentTimeMillis();
            int sum = dummyList.stream().reduce((integer, integer2) -> integer+integer2).get();
            System.out.println("Sum computed in this thread =  "+ sum);

            double stop = System.currentTimeMillis();
            System.out.println("Elapsed time in Multi threaded = " + (stop -start) +  "  ms");
        };
        ExecutorService service = Executors.newFixedThreadPool(5);
        List<Future> futures = new ArrayList<>(5);
        for(int i=0;i<5;i++){
            futures.add(service.submit(r));
        }

        futures.stream().forEach(future -> {
            try {
                future.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
        try {
            service.awaitTermination(2, TimeUnit.SECONDS);
            service.shutdown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ComputeReduction reduction = new  ComputeReduction();
        reduction.computeReductionSingleThreaded();
        reduction.computeReductionWithExecService();
    }
}
