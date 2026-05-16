import java.util.function.IntFunction;

class Solution {
    private int[] depth;
    private int[][] root;
    private long[][] dp;

    public int solution(int[] depth, int money, IntFunction<Integer> excavate) {
        this.depth = depth;

        int n = depth.length;

        root = new int[n][n];
        dp = new long[n][n];

        for (int len = 1; len <= n; len++) {
            for (int l = 0; l + len - 1 < n; l++) {
                int r = l + len - 1;

                dp[l][r] = Long.MAX_VALUE;

                for (int k = l; k <= r; k++) {
                    long left = k > l ? dp[l][k - 1] : 0;
                    long right = k < r ? dp[k + 1][r] : 0;

                    long cost = depth[k] + Math.max(left, right);

                    if (cost < dp[l][r]) {
                        dp[l][r] = cost;
                        root[l][r] = k;
                    }
                }
            }
        }

        int l = 0;
        int r = n - 1;

        while (l <= r) {
            int k = root[l][r];
            int result = excavate.apply(k + 1);

            if (result == 0) {
                return k + 1;
            }

            if (result == -1) {
                r = k - 1;
            } else {
                l = k + 1;
            }
        }

        return -1;
    }
}