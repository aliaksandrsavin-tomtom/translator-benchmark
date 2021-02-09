package com.tomtom.cpu.coredb;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

@Fork(value = 1)
public class TranslatorBenchmark {

    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Warmup(iterations = 5, timeUnit = TimeUnit.NANOSECONDS)
    @Measurement(iterations = 20, timeUnit = TimeUnit.NANOSECONDS)
    @Benchmark
    @BenchmarkMode(Mode.All)
    public void translate(ExecutionPlan executionPlan, Blackhole blackhole) {
        blackhole.consume(executionPlan.getTestBeans().translate(executionPlan.getLogicalTransaction()));
    }
}
