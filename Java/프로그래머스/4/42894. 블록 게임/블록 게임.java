class Solution {
    public int solution(int[][] board) {
        int answer = 0;

        while (true) {
            boolean removed = false;

            for (int r = 0; r < board.length; r++) {
                for (int c = 0; c < board.length; c++) {
                    if (board[r][c] != 0 && canRemove(board, r, c)) {
                        remove(board, board[r][c]);
                        answer++;
                        removed = true;
                    }
                }
            }

            if (!removed) {
                break;
            }
        }

        return answer;
    }

    private boolean canRemove(int[][] board, int row, int col) {
        int number = board[row][col];
        int minRow = board.length;
        int maxRow = 0;
        int minCol = board.length;
        int maxCol = 0;
        int count = 0;

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board.length; c++) {
                if (board[r][c] == number) {
                    minRow = Math.min(minRow, r);
                    maxRow = Math.max(maxRow, r);
                    minCol = Math.min(minCol, c);
                    maxCol = Math.max(maxCol, c);
                    count++;
                }
            }
        }

        if (count != 4) {
            return false;
        }

        int height = maxRow - minRow + 1;
        int width = maxCol - minCol + 1;

        if (height * width != 6) {
            return false;
        }

        for (int r = minRow; r <= maxRow; r++) {
            for (int c = minCol; c <= maxCol; c++) {
                if (board[r][c] == 0 && !canDrop(board, r, c)) {
                    return false;
                }

                if (board[r][c] != 0 && board[r][c] != number) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean canDrop(int[][] board, int row, int col) {
        for (int r = 0; r < row; r++) {
            if (board[r][col] != 0) {
                return false;
            }
        }

        return true;
    }

    private void remove(int[][] board, int number) {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board.length; c++) {
                if (board[r][c] == number) {
                    board[r][c] = 0;
                }
            }
        }
    }
}