import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private boolean[][]grid;
    private int N;
    private WeightedQuickUnionUF bro;
    private WeightedQuickUnionUF sis;
    private int top;
    private int bottom;
    private int openSets;

    public Percolation(int N) {
        this.N = N;
        grid = new boolean[N][N];
        top = N*N;
        bottom = N*N+1;
        bro = new WeightedQuickUnionUF(N*N+2);
        sis = new WeightedQuickUnionUF(N*N+1);
        openSets = 0;
        for (int i = 0; i < N; i++){
            bro.union(toNumber(0,i), top);
            sis.union(toNumber(0,i), top);
        }
    }

    private void isBroLegit(int row,int col){
        if ((row>N-1)||(col>N-1)||(row<0)||(col<0)){
            throw new java.lang.IndexOutOfBoundsException("bro is not fr");
        }
    }

    private int toNumber(int row, int col){
        return(row * N + col);
    }

    public void open(int row, int col) {
        isBroLegit(row,col);
        if (!isOpen(row,col)){
            grid[row][col] = true;
            if (row!=0) {
                if ((grid[row - 1][col])) {
                    bro.union((toNumber(row, col)), toNumber((row - 1), col));
                    sis.union((toNumber(row, col)), toNumber((row - 1), col));
                }
            }
            if (row!=N-1) {
                if ((grid[row + 1][col])) {
                    bro.union((toNumber(row, col)), toNumber((row + 1), col));
                    sis.union((toNumber(row, col)), toNumber((row + 1), col));
                }
            }
            if(col!=0) {
                if ((grid[row][col - 1])) {
                    bro.union((toNumber(row, col)), toNumber((row), col - 1));
                    sis.union((toNumber(row, col)), toNumber((row), col - 1));
                }
            }
            if(col!=N-1) {
                if ((grid[row][col + 1])) {
                    bro.union((toNumber(row, col)), toNumber((row), col + 1));
                    sis.union((toNumber(row, col)), toNumber((row), col + 1));
                }
            }

            if (row == N-1){
                bro.union(toNumber(N-1,col),bottom);
            }

            openSets+=1;
        }
    }

    public boolean isOpen(int row, int col) {
        isBroLegit(row,col);
        return (grid[row][col]);
    }

    public boolean isFull(int row, int col) {
        isBroLegit(row,col);
        return(sis.connected(toNumber(row,col), top)&&grid[row][col]);

    }

    public int numberOfOpenSites() {
        return (openSets);
    }

    public boolean percolates() {
        return(bro.connected(top,bottom));
    }

}
