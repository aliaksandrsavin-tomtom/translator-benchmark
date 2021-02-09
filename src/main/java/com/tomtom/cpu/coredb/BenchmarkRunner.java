package com.tomtom.cpu.coredb;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;

/**
 * Hello world!
 */
public class BenchmarkRunner {
    public static void main(String[] args) throws IOException, RunnerException {
        Options opt = new OptionsBuilder().forks(1).build();
        new Runner(opt).run();
    }
}
