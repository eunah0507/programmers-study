import java.util.ArrayDeque;

class Solution {
    public int solution(int[][] land) {
        int n = land.length;
        int m = land[0].length;
        int[] oilByColumn = new int[m];
        int[] columnMark = new int[m];
        int[] touchedColumns = new int[m];
        int mark = 0;
        int answer = 0;
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (land[r][c] == 0) {
                    continue;
                }

                mark++;
                int oilSize = 0;
                int touchedCount = 0;

                land[r][c] = 0;
                queue.offer(r * m + c);

                while (!queue.isEmpty()) {
                    int position = queue.poll();
                    int currentRow = position / m;
                    int currentColumn = position % m;
                    oilSize++;

                    if (columnMark[currentColumn] != mark) {
                        columnMark[currentColumn] = mark;
                        touchedColumns[touchedCount++] = currentColumn;
                    }

                    for (int direction = 0; direction < 4; direction++) {
                        int nextRow = currentRow + dr[direction];
                        int nextColumn = currentColumn + dc[direction];

                        if (nextRow < 0 || nextRow >= n || nextColumn < 0 || nextColumn >= m) {
                            continue;
                        }

                        if (land[nextRow][nextColumn] == 0) {
                            continue;
                        }

                        land[nextRow][nextColumn] = 0;
                        queue.offer(nextRow * m + nextColumn);
                    }
                }

                for (int i = 0; i < touchedCount; i++) {
                    oilByColumn[touchedColumns[i]] += oilSize;
                }
            }
        }

        for (int oil : oilByColumn) {
            answer = Math.max(answer, oil);
        }

        return answer;
    }
}