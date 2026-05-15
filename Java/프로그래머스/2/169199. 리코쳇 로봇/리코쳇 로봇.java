import java.util.*;

class Solution {
    public int solution(String[] board) {
        int n = board.length;
        int m = board[0].length();
        int startR = 0;
        int startC = 0;
        int goalR = 0;
        int goalC = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i].charAt(j) == 'R') {
                    startR = i;
                    startC = j;
                }

                if (board[i].charAt(j) == 'G') {
                    goalR = i;
                    goalC = j;
                }
            }
        }

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{startR, startC, 0});
        visited[startR][startC] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cur[0] == goalR && cur[1] == goalC) {
                return cur[2];
            }

            for (int i = 0; i < 4; i++) {
                int nr = cur[0];
                int nc = cur[1];

                while (true) {
                    int nextR = nr + dr[i];
                    int nextC = nc + dc[i];

                    if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= m || board[nextR].charAt(nextC) == 'D') {
                        break;
                    }

                    nr = nextR;
                    nc = nextC;
                }

                if (!visited[nr][nc]) {
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc, cur[2] + 1});
                }
            }
        }

        return -1;
    }
}