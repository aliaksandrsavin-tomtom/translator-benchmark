# Links

https://www.baeldung.com/java-microbenchmark-harness
http://tutorials.jenkov.com/java-performance/jmh.html
https://github.com/openjdk/jmh


# Result example

Result "com.tomtom.cpu.coredb.TranslatorBenchmark.translate":
  `N = 100
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
TranslatorBenchmark.translate                        ss      100  4784841.780 ± 380730.752   ns/op`
