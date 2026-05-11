class Solution {
    static final int MOD = 10000019;

    public int solution(int[][] a) {
        int n = a.length;
        int m = a[0].length;

        long[][] comb = new long[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            comb[i][0] = 1;
            comb[i][i] = 1;

            for (int j = 1; j < i; j++) {
                comb[i][j] = (comb[i - 1][j - 1] + comb[i - 1][j]) % MOD;
            }
        }

        int[] oneCount = new int[m];

        for (int col = 0; col < m; col++) {
            for (int row = 0; row < n; row++) {
                if (a[row][col] == 1) {
                    oneCount[col]++;
                }
            }
        }

        long[][] dp = new long[m + 1][n + 1];
        dp[0][0] = 1;

        for (int col = 0; col < m; col++) {
            int count = oneCount[col];

            for (int odd = 0; odd <= n; odd++) {
                if (dp[col][odd] == 0) {
                    continue;
                }

                for (int selectedOdd = 0; selectedOdd <= count; selectedOdd++) {
                    int selectedEven = count - selectedOdd;

                    if (selectedOdd > odd || selectedEven > n - odd) {
                        continue;
                    }

                    int nextOdd = odd - selectedOdd + selectedEven;
                    long ways = (comb[odd][selectedOdd] * comb[n - odd][selectedEven]) % MOD;

                    dp[col + 1][nextOdd] += dp[col][odd] * ways;
                    dp[col + 1][nextOdd] %= MOD;
                }
            }
        }

        return (int) dp[m][0];
    }
}