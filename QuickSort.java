package daa;

import java.util.Random;

public class QuickSort {

    private static final Random RND = new Random(42);

    public static void sort(int[] a, Metrics m) {
        if (a == null || a.length < 2) return;
        quickSort(a, 0, a.length - 1, m);
    }

    private static void quickSort(int[] a, int lo, int hi, Metrics m) {
        if (m != null) m.enter();
        try {
            while (lo < hi) {
                int pivotIndex = lo + RND.nextInt(hi - lo + 1);
                int pivot = a[pivotIndex];

                int lt = lo;
                int gt = hi;
                int i = lo;
                while (i <= gt) {
                    if (m != null) m.comparisons++;
                    if (a[i] < pivot) {
                        swap(a, lt, i);
                        lt++;
                        i++;
                    } else if (a[i] > pivot) {
                        swap(a, i, gt);
                        gt--;
                    } else {
                        i++;
                    }
                }

                int leftSize = lt - lo;
                int rightSize = hi - gt;

                if (leftSize < rightSize) {
                    quickSort(a, lo, lt - 1, m);
                    lo = gt + 1;
                } else {
                    quickSort(a, gt + 1, hi, m);
                    hi = lt - 1;
                }
            }
        } finally {
            if (m != null) m.exit();
        }
    }

    public static int[] partition3(int[] a, int lo, int hi, Metrics m) {
        int pivotIndex = lo + RND.nextInt(hi - lo + 1);
        int pivot = a[pivotIndex];
        int lt = lo;
        int gt = hi;
        int i = lo;
        while (i <= gt) {
            if (m != null) m.comparisons++;
            if (a[i] < pivot) {
                swap(a, lt, i);
                lt++;
                i++;
            } else if (a[i] > pivot) {
                swap(a, i, gt);
                gt--;
            } else {
                i++;
            }
        }
        return new int[]{lt, gt};
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}