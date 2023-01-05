package com.javaxpert.books.devjavapro.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode({Mode.Throughput,Mode.AverageTime})
@OutputTimeUnit(TimeUnit.MILLISECONDS)
//@Fork(value = 2,jvmArgs  ="-Xmx256M")
@State(Scope.Benchmark)
public class ComputeFiboBenchmark {

    @Param(value = "50")
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
            hole.consume(fibo.computeFiboMemoized(maxValue));
        //}
    }

}
