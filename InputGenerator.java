package daa;

import java.util.Random;

public class InputGenerator {

    public static int[] random(int n, long seed) {
        Random r = new Random(seed);
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = r.nextInt();
        }
        return a;
    }

    public static int[] sorted(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i;
        }
        return a;
    }

    public static int[] duplicates(int n, long seed) {
        Random r = new Random(seed);
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = r.nextInt(10);
        }
        return a;
    }
}