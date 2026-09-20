package daa;

public class Metrics {
    public long comparisons;
    public int maxDepth;
    public int currentDepth;

    public void reset() {
        comparisons = 0;
        maxDepth = 0;
        currentDepth = 0;
    }

    public void enter() {
        currentDepth++;
        if (currentDepth > maxDepth) {
            maxDepth = currentDepth;
        }
    }

    public void exit() {
        currentDepth--;
    }
}