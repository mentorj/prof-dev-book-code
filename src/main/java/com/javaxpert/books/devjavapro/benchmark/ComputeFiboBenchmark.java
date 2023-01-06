package com.javaxpert.books.devjavapro.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode({Mode.Throughput,Mode.SampleTime})
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 2, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 2, timeUnit = TimeUnit.SECONDS)
@Fork(value = 4,jvmArgs  ="-Xmx256M")
@State(Scope.Benchmark)
@Timeout(time = 10,timeUnit = TimeUnit.SECONDS)
public class ComputeFiboBenchmark {

    @Param({"5","10", "20","25","30" })
    int maxValue;

    ComputeFibo fibo;

    public static void main(String[] args) throws Exception{
        Options opt = new OptionsBuilder()
                .include(ComputeFiboBenchmark.class.getName())
                .forks(2)
                .build();
        new Runner(opt).run();

    }

    @Setup
    public void setUp(){
        fibo =  new ComputeFibo();
    }

    @Benchmark
    public void computeFibo(Blackhole hole){
        //for(int i=0;i<maxValue;i++) {
            hole.consume(fibo.computeFibo(maxValue));
        //}
    }

    @Benchmark
    public void computeFiboMemoized(Blackhole hole){
        //for(int i=0;i<maxValue;i++) {
            hole.consume(fibo.memoizedeFibo.apply(maxValue));
        //}
    }

}
