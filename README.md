# DAA Assignment 1 — Fast Sorting & Selection Engine

Divide and Conquer implementations of MergeSort, QuickSort and QuickSelect in Java,
with a benchmark comparing time, comparisons and recursion depth.

## Requirements

- JDK 17 or newer
- Maven (the one bundled with IntelliJ IDEA is enough)

## Project structure

```
src/main/java/daa/
  Metrics.java          - counters for comparisons, depth and time
  InsertionSort.java    - cutoff sort for small subarrays
  MergeSort.java        - one-buffer merge sort with cutoff 15
  QuickSort.java        - random pivot, 3-way partition, bounded depth
  QuickSelect.java      - k-th smallest, reuses QuickSort.partition3
  InputGenerator.java   - random / sorted / duplicates arrays
  Benchmark.java        - runs all cases, writes results.csv
  Main.java             - entry point

src/test/java/daa/
  MergeSortTest.java
  QuickSortTest.java
  QuickSelectTest.java
  EdgeCasesTest.java
```

## Build

Open the project in IntelliJ IDEA. Maven will download JUnit automatically.

If you use the terminal and have Maven installed:

```
mvn clean package
```

## Run tests

- In IntelliJ: open the Maven panel on the right, then
  `DaaBazaralyRamazan` -> `Lifecycle` -> double-click `test`.
- Or in the terminal: `mvn test`

Expected output:

```
Tests run: 9, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

## Run benchmark

- Open `src/main/java/daa/Main.java`
- Click the green triangle next to `public static void main`
- Wait about 15-30 seconds

This creates `results.csv` in the project root with columns:

```
algorithm,input,n,time_ms,comparisons,max_depth
```

## Inputs and sizes

- sizes: 1000, 10 000, 100 000, 1 000 000
- inputs: random integers, sorted array, values 0..9 (many duplicates)
- each case is run 5 times; the median time is reported

## Algorithms

- **MergeSort** — single reusable buffer, insertion sort cutoff for subarrays
  of size <= 15, linear merge.
- **QuickSort** — random pivot, 3-way partition (< pivot, = pivot, > pivot),
  recursion into the smaller side, larger side handled by a loop.
  Depth stays near log n even on sorted input.
- **QuickSelect** — returns the k-th smallest element (k starts at 0),
  reuses the same 3-way partition, continues only into the side containing k.

## Report

See `REPORT.md` for asymptotic bounds, recurrences, plots and discussion.