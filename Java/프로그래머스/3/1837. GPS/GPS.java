import java.util.*;

class Solution {
    public int solution(int n, int m, int[][] edgeList, int k, int[] gpsLog) {
        int inf = 1_000_000;
        ArrayList<Integer>[] graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            graph[i].add(i);
        }

        for (int[] edge : edgeList) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        int[][] dp = new int[k][n + 1];

        for (int i = 0; i < k; i++) {
            Arrays.fill(dp[i], inf);
        }

        dp[0][gpsLog[0]] = 0;

        for (int i = 1; i < k; i++) {
            for (int now = 1; now <= n; now++) {
                int cost = now == gpsLog[i] ? 0 : 1;

                for (int prev : graph[now]) {
                    dp[i][now] = Math.min(dp[i][now], dp[i - 1][prev] + cost);
                }
            }
        }

        int answer = dp[k - 1][gpsLog[k - 1]];

        if (answer >= inf) {
            return -1;
        }

        return answer;
    }
}