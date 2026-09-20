package daa;

public class QuickSelect {

    public static int select(int[] a, int k, Metrics m) {
        if (a == null || a.length == 0) {
            throw new IllegalArgumentException("Array is empty or null");
        }
        if (k < 0 || k >= a.length) {
            throw new IllegalArgumentException("k is out of range: " + k);
        }
        if (m != null) m.enter();
        try {
            return selectHelper(a, 0, a.length - 1, k, m);
        } finally {
            if (m != null) m.exit();
        }
    }

    private static int selectHelper(int[] a, int lo, int hi, int k, Metrics m) {
        while (true) {
            if (lo == hi) {
                return a[lo];
            }
            int[] range = QuickSort.partition3(a, lo, hi, m);
            int lt = range[0];
            int gt = range[1];

            if (k < lt) {
                hi = lt - 1;
            } else if (k > gt) {
                lo = gt + 1;
            } else {
                return a[k];
            }
        }
    }
}