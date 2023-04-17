package com.javaxpert.books.devjavapro.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Reduction over a list showcase mixing different threading policies
 * @author deadbrain
 */
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 2,timeUnit = TimeUnit.SECONDS,time = 5)
@State(Scope.Benchmark)
@Measurement(iterations = 3,timeUnit =TimeUnit.SECONDS,time = 1)
public class ListReductionBenchmark {
    private List<Integer> valuesListMutable;
    private io.vavr.collection.List<Integer> valuesListImmutable;

    @Param({"100","1000","10000","100000"})
    private int maxElements;

    private ExecutorService service;
    @Param({"1","3","5"})
    private int threadPoolSize;

    private Runnable reductionOverMutable = () -> {
      valuesListMutable.stream().reduce((integer, integer2) -> integer+integer2).get();
    };

    private Runnable reductionOverImmutable = () ->
    {
        valuesListImmutable.reduce((integer, integer2) -> integer+integer2).intValue();
    };

    @Setup()
    public void setUp(){
        valuesListMutable=new ArrayList<>(maxElements);
        for(int i =0;i<maxElements;i++){
            valuesListMutable.add(i);
        }
        valuesListImmutable= io.vavr.collection.List.ofAll(valuesListMutable);
        System.out.println("Benchmark is ready ...Setup  finished");
    }

    @Benchmark
    public void reductionSingleThreaded(Blackhole hole){
        for(int i =0;i<5;i++) {
            hole.consume(valuesListMutable.stream().reduce((integer, integer2) -> integer + integer2).get());
        }
    }

    @Benchmark
    public void reductionMutableMultiThreaded(Blackhole hole){
        service= Executors.newFixedThreadPool(threadPoolSize);
        for(int i =0;i<5;i++){
            Future f = service.submit(reductionOverMutable);
            try {
                hole.consume(f.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        service.shutdown();
    }

    @Benchmark
    public void reductionImmutableMultiThreaded(Blackhole hole){
        service= Executors.newFixedThreadPool(threadPoolSize);
        for(int i =0;i<5;i++){
            Future f = service.submit(reductionOverImmutable);
            try {
                hole.consume(f.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        service.shutdown();
    }

    public static void main(String[] args) throws Exception{
        Options opt = new OptionsBuilder()
                .include(ListReductionBenchmark.class.getName())
                .build();
        new Runner(opt).run();

    }

}
