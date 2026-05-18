class Solution {
    public int solution(int[][] visible, int[][] hidden, int k) {
        int n = visible.length;
        int m = visible[0].length;
        int answer = Integer.MIN_VALUE;
        boolean needRemove = n % 2 == 0 && m % 2 == 0;

        for (int mask = 0; mask < (1 << n); mask++) {
            int rowCost = Integer.bitCount(mask) * k;

            if (!needRemove) {
                int total = -rowCost;

                for (int j = 0; j < m; j++) {
                    int sum0 = 0;
                    int sum1 = -k;

                    for (int i = 0; i < n; i++) {
                        boolean rowFlip = (mask & (1 << i)) != 0;

                        sum0 += rowFlip ? hidden[i][j] : visible[i][j];
                        sum1 += rowFlip ? visible[i][j] : hidden[i][j];
                    }

                    total += Math.max(sum0, sum1);
                }

                answer = Math.max(answer, total);
            } else {
                int[] dp = new int[102];
                for (int i = 0; i < dp.length; i++) {
                    dp[i] = -1_000_000_000;
                }
                dp[101] = 0;

                for (int j = 0; j < m; j++) {
                    int sum0 = 0;
                    int sum1 = -k;
                    int min0 = 101;
                    int min1 = 101;

                    for (int i = 0; i < n; i++) {
                        boolean rowFlip = (mask & (1 << i)) != 0;
                        int value0 = rowFlip ? hidden[i][j] : visible[i][j];
                        int value1 = rowFlip ? visible[i][j] : hidden[i][j];

                        sum0 += value0;
                        sum1 += value1;

                        if ((i + j) % 2 == 1) {
                            min0 = Math.min(min0, value0);
                            min1 = Math.min(min1, value1);
                        }
                    }

                    int[] next = new int[102];
                    for (int i = 0; i < next.length; i++) {
                        next[i] = -1_000_000_000;
                    }

                    for (int min = 1; min <= 101; min++) {
                        if (dp[min] == -1_000_000_000) {
                            continue;
                        }

                        int nextMin0 = Math.min(min, min0);
                        int nextMin1 = Math.min(min, min1);

                        next[nextMin0] = Math.max(next[nextMin0], dp[min] + sum0);
                        next[nextMin1] = Math.max(next[nextMin1], dp[min] + sum1);
                    }

                    dp = next;
                }

                for (int min = 1; min <= 100; min++) {
                    answer = Math.max(answer, dp[min] - min - rowCost);
                }
            }
        }

        return answer;
    }
}