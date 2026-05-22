class Solution {
    public int solution(int[][] cost, int[][] hint) {
        int n = cost.length;
        int answer = Integer.MAX_VALUE;

        for (int mask = 0; mask < (1 << (n - 1)); mask++) {
            int[] cnt = new int[n];
            int sum = 0;

            for (int i = 0; i < n; i++) {
                int use = Math.min(cnt[i], n - 1);
                sum += cost[i][use];

                if (i < n - 1 && (mask & (1 << i)) != 0) {
                    sum += hint[i][0];

                    for (int j = 1; j < hint[i].length; j++) {
                        cnt[hint[i][j] - 1]++;
                    }
                }
            }

            answer = Math.min(answer, sum);
        }

        return answer;
    }
}