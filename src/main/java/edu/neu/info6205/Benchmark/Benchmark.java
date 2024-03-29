package edu.neu.info6205.Benchmark;
import java.util.function.Supplier;
//credit: INFO6205 class repo for benchmark analysis only
/**
 * Interface to define the behavior of a Benchmark.
 *
 * @param <T> the underlying type which is passed into (or supplied) to the run/runFromSupplier methods.
 */
public interface Benchmark<T> {
    /**
     * Run function f m times and return the average time in milliseconds.
     *
     * @param t the value that will in turn be passed to function f.
     * @param m the number of times the function f will be called.
     * @param warmup
     * @return the average number of milliseconds taken for each run of function f.
     */
    default double run(T t, int m, boolean warmup) {
        return runFromSupplier(() -> t, m, warmup);
    }

    /**
     * Run function f m times and return the average time in milliseconds.
     *
     * @param supplier a Supplier of a T
     * @param m        the number of times the function f will be called.
     * @return the average number of milliseconds taken for each run of function f.
     */
    double runFromSupplier(Supplier<T> supplier, int m,boolean warmup);

}


