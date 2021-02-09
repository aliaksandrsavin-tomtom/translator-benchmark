# Links

https://www.baeldung.com/java-microbenchmark-harness
http://tutorials.jenkov.com/java-performance/jmh.html
https://github.com/openjdk/jmh


JMH is short for Java Microbenchmark Harness.
JMH is developed by the same people who implement the Java virtual machine
JVM makes many optimizations

@Benchmark

@BenchmarkMode - JMH supports some possible benchmarks: Throughput, AverageTime, SampleTime, and SingleShotTime.

@Fork(1) - how many times the benchmark will be executed

@Warmup(iterations = 10, timeUnit = TimeUnit.NANOSECONDS) - annotation can be used to control the number of warmup iterations.

@Measurement(iterations = 1000, timeUnit = TimeUnit.NANOSECONDS) - this is the measurement itself

batchSize = 10

-----------------------------------------------------------------------------------------------------------------------

# Dead Code Elimination
Blackhole

When running microbenchmarks, it's very important to be aware of optimizations

@Benchmark
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
public void doNothing() {
}

@Benchmark
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
public void objectCreation() {
    new Object();
}

We expect object allocation costs more than doing nothing at all. However, if we run the benchmarks:

Benchmark                 Mode  Cnt  Score   Error  Units
BenchMark.doNothing       avgt   40  0.609 ± 0.006  ns/op
BenchMark.objectCreation  avgt   40  0.613 ± 0.007  ns/op

In order to prevent this optimization, we should somehow trick the compiler and make it think that the code is used by some other component.
One way to achieve this is just to return the created object.

@Benchmark
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
public Object pillarsOfCreation() {
    return new Object();
}

Also, we can let the Blackhole consume it.

@Benchmark
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
public void blackHole(Blackhole blackhole) {
    blackhole.consume(new Object());
}

Having Blackhole consume the object is a way to convince the JIT compiler to not apply the dead code elimination optimization.


# Result example

Result "com.tomtom.cpu.coredb.TranslatorBenchmark.translate":
  N = 100
  mean = 4784841.780 ±(99.9%) 380730.752 ns/op

  Histogram, ns/op:
    [2000000.000, 2500000.000) = 0
    [2500000.000, 3000000.000) = 5
    [3000000.000, 3500000.000) = 10
    [3500000.000, 4000000.000) = 15
    [4000000.000, 4500000.000) = 9
    [4500000.000, 5000000.000) = 18
    [5000000.000, 5500000.000) = 15
    [5500000.000, 6000000.000) = 15
    [6000000.000, 6500000.000) = 5
    [6500000.000, 7000000.000) = 6
    [7000000.000, 7500000.000) = 1

  Percentiles, ns/op:
      p(0.0000) = 2768929.000 ns/op
     p(50.0000) = 4851437.000 ns/op
     p(90.0000) = 6290854.000 ns/op
     p(95.0000) = 6781271.650 ns/op
     p(99.0000) = 7697742.300 ns/op
     p(99.9000) = 7702076.000 ns/op
     p(99.9900) = 7702076.000 ns/op
     p(99.9990) = 7702076.000 ns/op
     p(99.9999) = 7702076.000 ns/op
    p(100.0000) = 7702076.000 ns/op


Run complete. Total time: 00:06:12

Benchmark                                          Mode      Cnt        Score        Error   Units
TranslatorBenchmark.translate                     thrpt      100       ≈ 10⁻⁴               ops/ns
TranslatorBenchmark.translate                      avgt      100    16443.675 ±    177.062   ns/op
TranslatorBenchmark.translate                    sample  2666106    17616.144 ±     58.125   ns/op
TranslatorBenchmark.translate:translate·p0.00    sample             14768.000                ns/op
TranslatorBenchmark.translate:translate·p0.50    sample             16608.000                ns/op
TranslatorBenchmark.translate:translate·p0.90    sample             17408.000                ns/op
TranslatorBenchmark.translate:translate·p0.95    sample             17728.000                ns/op
TranslatorBenchmark.translate:translate·p0.99    sample             29536.000                ns/op
TranslatorBenchmark.translate:translate·p0.999   sample            397312.000                ns/op
TranslatorBenchmark.translate:translate·p0.9999  sample            434688.000                ns/op
TranslatorBenchmark.translate:translate·p1.00    sample           6062080.000                ns/op
TranslatorBenchmark.translate                        ss      100  4784841.780 ± 380730.752   ns/op