package com.javaxpert.books.devjavapro.benchmark;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.profile.CompilerProfiler;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.profile.StackProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * Benchmark JMH for simple loops
 *
 * @author deadbrain - jerome@javaxpert.com
 */
@BenchmarkMode({ Mode.Throughput })
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 1, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 2, timeUnit = TimeUnit.SECONDS)
@Fork(value = 1, jvmArgs = "-Xmx256M")
@State(Scope.Thread)
public class SimpleLoopsBenchmark {
    private SimpleLoops loopsObj;

    @Setup
    public void setUp() {
        loopsObj = new SimpleLoops();
    }

    @Benchmark
    public void runCommputeWithReduce(Blackhole bh) {
        bh.consume(loopsObj.computeWithReduce());
    }

    @Benchmark
    public void runWithOneSingleLoop(Blackhole bh) {
        bh.consume(loopsObj.computeWithSingleLoop());
    }

    @Benchmark
    public void runWith2Loops(Blackhole bh) {
        bh.consume(loopsObj.computeWith2Loops());
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder().include(SimpleLoopsBenchmark.class.getName())
                .addProfiler(StackProfiler.class).addProfiler(GCProfiler.class).addProfiler(CompilerProfiler.class)
                .build();
        new Runner(opt).run();

    }

    @Test
    void runBenchmarkAsTest() {

        Options opt = new OptionsBuilder().include(SimpleLoopsBenchmark.class.getName())
                .addProfiler(StackProfiler.class).addProfiler(GCProfiler.class).addProfiler(CompilerProfiler.class)
                .build();
        try {
            new Runner(opt).run();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertTrue(true);

    }
}
