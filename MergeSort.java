package daa;

public class MergeSort {

    private static final int CUTOFF = 15;

    public static void sort(int[] a, Metrics m) {
        if (a == null || a.length < 2) return;
        int[] buffer = new int[a.length];
        mergeSort(a, buffer, 0, a.length - 1, m);
    }

    private static void mergeSort(int[] a, int[] buffer, int lo, int hi, Metrics m) {
        if (m != null) m.enter();
        try {
            if (hi - lo + 1 <= CUTOFF) {
                InsertionSort.sort(a, lo, hi, m);
                return;
            }
            int mid = lo + (hi - lo) / 2;
            mergeSort(a, buffer, lo, mid, m);
            mergeSort(a, buffer, mid + 1, hi, m);
            merge(a, buffer, lo, mid, hi, m);
        } finally {
            if (m != null) m.exit();
        }
    }

    private static void merge(int[] a, int[] buffer, int lo, int mid, int hi, Metrics m) {
        int i = lo;
        int j = mid + 1;
        int k = lo;

        while (i <= mid && j <= hi) {
            if (m != null) m.comparisons++;
            if (a[i] <= a[j]) {
                buffer[k++] = a[i++];
            } else {
                buffer[k++] = a[j++];
            }
        }
        while (i <= mid) {
            buffer[k++] = a[i++];
        }
        while (j <= hi) {
            buffer[k++] = a[j++];
        }
        for (int t = lo; t <= hi; t++) {
            a[t] = buffer[t];
        }
    }
}