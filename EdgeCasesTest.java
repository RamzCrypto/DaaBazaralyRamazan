package daa;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EdgeCasesTest {

    @Test
    public void emptyArray() {
        int[] a = {};
        Metrics m = new Metrics();
        MergeSort.sort(a, m);
        QuickSort.sort(a, m);
        assertArrayEquals(new int[]{}, a);
    }

    @Test
    public void oneElement() {
        int[] a = {42};
        Metrics m = new Metrics();
        MergeSort.sort(a, m);
        QuickSort.sort(a, m);
        assertArrayEquals(new int[]{42}, a);
    }

    @Test
    public void allEqual() {
        int[] a = new int[1000];
        for (int i = 0; i < a.length; i++) a[i] = 7;

        int[] b = a.clone();
        Metrics m1 = new Metrics();
        MergeSort.sort(a, m1);
        Metrics m2 = new Metrics();
        QuickSort.sort(b, m2);

        for (int x : a) assertEquals(7, x);
        for (int x : b) assertEquals(7, x);
    }

    @Test
    public void alreadySorted() {
        int[] a = InputGenerator.sorted(5000);
        int[] b = a.clone();

        Metrics m1 = new Metrics();
        MergeSort.sort(a, m1);
        Metrics m2 = new Metrics();
        QuickSort.sort(b, m2);

        for (int i = 0; i < a.length; i++) {
            assertEquals(i, a[i]);
            assertEquals(i, b[i]);
        }
    }

    @Test
    public void selectInvalidInput() {
        int[] a = {};
        assertThrows(IllegalArgumentException.class,
                () -> QuickSelect.select(a, 0, new Metrics()));

        int[] b = {1, 2, 3};
        assertThrows(IllegalArgumentException.class,
                () -> QuickSelect.select(b, -1, new Metrics()));
        assertThrows(IllegalArgumentException.class,
                () -> QuickSelect.select(b, 3, new Metrics()));
    }
}