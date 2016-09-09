import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int[][] sites;
    private WeightedQuickUnionUF uf;
    private int sitesCount;

    /**
     * Constructor - Creates a n-by-n grid system
     * @param n  size of the N-by-N system grid
     */
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException();

        sites = new int[n][n]; // will be initialized to 0s(blocked)
        sitesCount = n * n;
        // Two extra nodes for virtual top and bottom sites
        uf = new WeightedQuickUnionUF(sitesCount + 2);

        // Connect sites in the top row to the virtual top site
        for (int idx = 0; idx < n; idx++)
            uf.union(idx, sitesCount);

        // Connect sites in the bottom row to the virtual bottom site
        for (int idx = sitesCount - 1; idx > sitesCount - n - 1; idx--)
            uf.union(idx, sitesCount + 1);
    }

    /**
     * Open site in row i and column j if its not open already
     * @param i  row index
     * @param j  column index
     */
    public void open(int i, int j) {
        validateIndices(i, j);

        if (!isOpen(i, j)) {
            sites[i - 1][j - 1] = 1;

            // Convert 1-based indices to 0-based indices
            int x = i - 1;
            int y = j - 1;

            // Declare variable for xy to 1D conversion
            int ufIdxOne, ufIdxTwo;

            // Generate adjacent site indices(top, bottom, left and right)
            int[][] adjacentIndices = new int[][]{
                    {x - 1, y}, {x + 1, y}, {x, y - 1}, {x, y + 1}
            };

            // Check if adjacent sites are open. And if they are open, connect them

            for (int idx = 0; idx < 4; idx++) {
                int xIndex = adjacentIndices[idx][0];
                int yIndex = adjacentIndices[idx][1];
                if (isValid(xIndex, yIndex) &&
                        isOpen(xIndex + 1, yIndex + 1)) {
                    ufIdxOne = to1D(x, y);
                    ufIdxTwo = to1D(xIndex, yIndex);

                    // Connect the adjacent open sites
                    uf.union(ufIdxOne, ufIdxTwo);
                }
            }
        }
    }

    /**
     * Check if a site at position (i, j) is open(1)
     * @param i row index
     * @param j column index
     * @return true if the site is open, false otherwise
     */
    public boolean isOpen(int i, int j) {
        validateIndices(i, j);
        // i  and j are 1 based
        return sites[i-1][j-1] == 1;
    }

    /**
     *
     * @param i row index
     * @param j column index
     * @return true if the site is full, false otherwise
     */
    public boolean isFull(int i, int j) {
        validateIndices(i, j);
        int ufIdx = to1D(i - 1, j - 1);
        return isOpen(i, j) && uf.connected(ufIdx, sitesCount);
    }

    /**
     *
     * @return true if the system percolates(a path exists from the top to bottom)
     */
    public boolean percolates() {
        // Check if the virtual topsite and bottomsite is connected
        return uf.connected(sitesCount, sitesCount + 1);
    }

    /**
     * Maps 0-based indices from the grid(x,y) to one-dimensional index
     * for WeightedUnionFind object
     * @param i row index
     * @param j column index
     * @return the corresponding one-dimensional index
     */
    private int to1D(int i, int j) {
        return i * sites.length + j;
    }

    /**
     * Validates the 1-based indices and raises exception if they aren't
     * @param i row index
     * @param j column index
     *
     */
    private void validateIndices(int i, int j) {
        if (i < 1 || i > sites.length || j < 1 || j > sites.length) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Validates the 0-based indices of the grid
     * @param i row index
     * @param j column index
     * @return true if the pair of index lies within the grid, false otherwise
     */
    private boolean isValid(int i, int j) {
        return (i >= 0 && i < sites.length) && (j >= 0 && j < sites.length);
    }

}
