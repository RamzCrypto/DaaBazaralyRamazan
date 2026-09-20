package daa;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Benchmark {

    private static final int[] SIZES = {1000, 10000, 100000, 1000000};
    private static final String[] INPUTS = {"random", "sorted", "duplicates"};
    private static final int REPEATS = 5;

    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter("results.csv"));
        out.println("algorithm,input,n,time_ms,comparisons,max_depth");

        for (String input : INPUTS) {
            for (int n : SIZES) {
                runCase(out, "MergeSort", input, n);
                runCase(out, "QuickSort", input, n);
                runCase(out, "QuickSelect", input, n);
            }
        }
        out.close();
        System.out.println("Done. See results.csv");
    }

    private static void runCase(PrintWriter out, String algo, String input, int n) {
        long[] times = new long[REPEATS];
        long comparisons = 0;
        int maxDepth = 0;

        for (int r = 0; r < REPEATS; r++) {
            int[] a = makeInput(input, n, r);
            Metrics m = new Metrics();
            long t0 = System.nanoTime();

            if (algo.equals("MergeSort")) {
                MergeSort.sort(a, m);
            } else if (algo.equals("QuickSort")) {
                QuickSort.sort(a, m);
            } else {
                int k = n / 2;
                QuickSelect.select(a, k, m);
            }

            long t1 = System.nanoTime();
            times[r] = (t1 - t0) / 1_000_000;
            if (r == REPEATS - 1) {
                comparisons = m.comparisons;
                maxDepth = m.maxDepth;
            }
        }
        Arrays.sort(times);
        long median = times[REPEATS / 2];

        out.println(algo + "," + input + "," + n + "," + median + ","
                + comparisons + "," + maxDepth);
        out.flush();
    }

    private static int[] makeInput(String input, int n, int seed) {
        if (input.equals("random")) return InputGenerator.random(n, seed);
        if (input.equals("sorted")) return InputGenerator.sorted(n);
        return InputGenerator.duplicates(n, seed);
    }
}