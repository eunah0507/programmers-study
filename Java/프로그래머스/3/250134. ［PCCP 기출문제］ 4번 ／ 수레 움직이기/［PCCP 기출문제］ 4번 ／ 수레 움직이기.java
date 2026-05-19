class Solution {
    int n;
    int m;
    int[][] maze;
    int redGoal;
    int blueGoal;
    int answer = Integer.MAX_VALUE;

    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};

    public int solution(int[][] maze) {
        this.maze = maze;
        n = maze.length;
        m = maze[0].length;

        int redStart = 0;
        int blueStart = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int pos = i * m + j;

                if (maze[i][j] == 1) {
                    redStart = pos;
                } else if (maze[i][j] == 2) {
                    blueStart = pos;
                } else if (maze[i][j] == 3) {
                    redGoal = pos;
                } else if (maze[i][j] == 4) {
                    blueGoal = pos;
                }
            }
        }

        dfs(redStart, blueStart, 1 << redStart, 1 << blueStart, 0);

        if (answer == Integer.MAX_VALUE) {
            return 0;
        }

        return answer;
    }

    void dfs(int red, int blue, int redVisited, int blueVisited, int count) {
        if (count >= answer) {
            return;
        }

        if (red == redGoal && blue == blueGoal) {
            answer = count;
            return;
        }

        int[] redMoves = getMoves(red, redGoal, redVisited);
        int[] blueMoves = getMoves(blue, blueGoal, blueVisited);

        for (int nr : redMoves) {
            if (nr == -1) {
                continue;
            }

            for (int nb : blueMoves) {
                if (nb == -1) {
                    continue;
                }

                if (nr == nb) {
                    continue;
                }

                if (nr == blue && nb == red) {
                    continue;
                }

                dfs(
                    nr,
                    nb,
                    redVisited | (1 << nr),
                    blueVisited | (1 << nb),
                    count + 1
                );
            }
        }
    }

    int[] getMoves(int pos, int goal, int visited) {
        int[] result = {-1, -1, -1, -1};

        if (pos == goal) {
            result[0] = pos;
            return result;
        }

        int r = pos / m;
        int c = pos % m;

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr < 0 || nr >= n || nc < 0 || nc >= m) {
                continue;
            }

            if (maze[nr][nc] == 5) {
                continue;
            }

            int next = nr * m + nc;

            if ((visited & (1 << next)) != 0) {
                continue;
            }

            result[i] = next;
        }

        return result;
    }
}