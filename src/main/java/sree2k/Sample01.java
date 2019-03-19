package sree2k;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.profile.Profiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class Sample01 {

    private static Random random = new Random();

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public LinkedHashSet<Integer> testLinkedHashSet() {
        LinkedHashSet<Integer> set = new LinkedHashSet<>();

        for(int i = 0; i < 10000; i++) {
            set.add(random.nextInt(1000000));
        }

        return set;
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public HashSet<Integer> testHashSet() {
        HashSet<Integer> set = new HashSet<>();

        for(int i = 0; i < 10000; i++) {
            set.add(random.nextInt(1000000));
        }

        return set;
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public TreeSet<Integer> testTreeSet() {
        TreeSet<Integer> set = new TreeSet<>();

        for(int i = 0; i < 10000; i++) {
            set.add(random.nextInt(1000000));
        }

        return set;
    }

    public static void main(String[] args) throws Exception {
        Options opt = new OptionsBuilder()
                .include(Sample01.class.getSimpleName())
                .forks(1)
                .addProfiler(GCProfiler.class)
                .measurementIterations(2)
                .build();

        new Runner(opt).run();
    }
}