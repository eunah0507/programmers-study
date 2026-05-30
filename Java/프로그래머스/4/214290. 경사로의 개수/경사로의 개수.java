class Solution {
    static final long MOD = 1_000_000_007L;

    public int solution(int[][] grid, int[] d, int k) {
        int n = grid.length;
        int m = grid[0].length;
        int size = n * m;

        long[][] matrix = identity(size);

        for (int slope : d) {
            long[][] next = new long[size][size];

            for (int r = 0; r < n; r++) {
                for (int c = 0; c < m; c++) {
                    int from = r * m + c;

                    if (r > 0 && grid[r - 1][c] - grid[r][c] == slope) {
                        next[from][(r - 1) * m + c]++;
                    }
                    if (r < n - 1 && grid[r + 1][c] - grid[r][c] == slope) {
                        next[from][(r + 1) * m + c]++;
                    }
                    if (c > 0 && grid[r][c - 1] - grid[r][c] == slope) {
                        next[from][r * m + c - 1]++;
                    }
                    if (c < m - 1 && grid[r][c + 1] - grid[r][c] == slope) {
                        next[from][r * m + c + 1]++;
                    }
                }
            }

            matrix = multiply(matrix, next);
        }

        long[][] result = power(matrix, k);
        long answer = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                answer = (answer + result[i][j]) % MOD;
            }
        }

        return (int) answer;
    }

    private long[][] power(long[][] matrix, int exp) {
        int size = matrix.length;
        long[][] result = identity(size);

        while (exp > 0) {
            if (exp % 2 == 1) {
                result = multiply(result, matrix);
            }

            matrix = multiply(matrix, matrix);
            exp /= 2;
        }

        return result;
    }

    private long[][] multiply(long[][] a, long[][] b) {
        int size = a.length;
        long[][] result = new long[size][size];

        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                if (a[i][k] == 0) {
                    continue;
                }

                for (int j = 0; j < size; j++) {
                    if (b[k][j] == 0) {
                        continue;
                    }

                    result[i][j] = (result[i][j] + a[i][k] * b[k][j]) % MOD;
                }
            }
        }

        return result;
    }

    private long[][] identity(int size) {
        long[][] result = new long[size][size];

        for (int i = 0; i < size; i++) {
            result[i][i] = 1;
        }

        return result;
    }
}