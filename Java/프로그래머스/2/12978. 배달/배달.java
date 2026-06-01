import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        int[][] dist = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], 500001);
            dist[i][i] = 0;
        }

        for (int[] r : road) {
            int a = r[0];
            int b = r[1];
            int c = r[2];

            if (dist[a][b] > c) {
                dist[a][b] = c;
                dist[b][a] = c;
            }
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        int answer = 0;

        for (int i = 1; i <= N; i++) {
            if (dist[1][i] <= K) {
                answer++;
            }
        }

        return answer;
    }
}