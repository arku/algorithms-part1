import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] thresholds;
    private int trials;
    /**
     * Perform trials independent experiments on a n-by-n grid
     * @param n         size of the grid
     * @param trials    number of trials
     */
    public PercolationStats(int n, int trials) {

        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }

        thresholds = new double[trials];
        this.trials = trials;

        for (int idx = 0; idx < trials; idx++) {
            Percolation percolation = new Percolation(n);
            int sitesOpened = 0;

            while (!percolation.percolates()) {
                int randX = StdRandom.uniform(n) + 1;
                int randY = StdRandom.uniform(n) + 1;
                if (!percolation.isOpen(randX, randY)) {
                    percolation.open(randX, randY);
                    sitesOpened += 1;
                }
            }
            thresholds[idx] = (double) sitesOpened / (n * n);
        }
    }

    /**
     *
     * @return sample mean of percolation threshold
     */
    public double mean() {
        return StdStats.mean(thresholds);
    }

    /**
     *
     * @return the standard deviation of percolation threshold
     */
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    /**
     *
     * @return low  endpoint of 95% confidence interval
     */
    public double confidenceLo() {
        return mean() - (1.96 * stddev() / Math.sqrt(trials));
    }

    /**
     *
     * @return high endpoint of 95% confidence interval
     */
    public double confidenceHi() {
        return mean() + (1.96 * stddev() / Math.sqrt(trials));
    }

    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]), trials = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, trials);

        StdOut.println("mean = " + stats.mean());
        StdOut.println("stddev = " + stats.stddev());
        String interval = stats.confidenceLo() + " , " + stats.confidenceHi();
        StdOut.println("95% confidence interval = " + interval);

    }
}
