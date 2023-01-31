package com.javaxpert.books.devjavapro.benchmark;

import io.vavr.collection.Stream;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import java.util.List;
import java.util.concurrent.TimeUnit;

@BenchmarkMode({Mode.Throughput,Mode.SampleTime})
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 2, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 2, timeUnit = TimeUnit.SECONDS)
@Fork(value = 4,jvmArgs  ="-Xmx256M")
@State(Scope.Benchmark)
@Timeout(time = 10,timeUnit = TimeUnit.SECONDS)
/**
 * Basic benchmark for samples
 * @author deadbrain
 */
public class BasicSamplesBenchmark {

    @Param({"50","100", "200","250","300" })
    int maxValue;

    private BasicSamples samples;

    @Setup
    public void setUp(){
        System.out.println("Setup with maxValue ="  + maxValue);
        List<Integer> values =  Stream.from(1).take(maxValue).toJavaList();
        samples = new BasicSamples(values);
        System.out.println("Instantiated samples");
    }

    @Benchmark
    public void callCOmputeLoopShorter(Blackhole hole){
      hole.consume(samples.computeLoopShorter());
    }

    @Benchmark
    public void callComputeWithLoop(Blackhole  hole){
        hole.consume(samples.computeLoopStandardWithLoop());
    }

    public static void main(String[] args) throws Exception{
        Options opt = new OptionsBuilder()
                .include(BasicSamplesBenchmark.class.getName())
                .build();
        new Runner(opt).run();

    }
}
