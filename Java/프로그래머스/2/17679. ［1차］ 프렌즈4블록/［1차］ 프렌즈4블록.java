class Solution {
    public int solution(int m, int n, String[] board) {
        char[][] map = new char[m][n];
        int answer = 0;
        
        for (int i = 0; i < m; i++) {
            map[i] = board[i].toCharArray();
        }
        
        while (true) {
            boolean[][] remove = new boolean[m][n];
            int count = 0;
            
            for (int i = 0; i < m - 1; i++) {
                for (int j = 0; j < n - 1; j++) {
                    char block = map[i][j];
                    
                    if (block == ' ') {
                        continue;
                    }
                    
                    if (block == map[i][j + 1] && block == map[i + 1][j] && block == map[i + 1][j + 1]) {
                        remove[i][j] = true;
                        remove[i][j + 1] = true;
                        remove[i + 1][j] = true;
                        remove[i + 1][j + 1] = true;
                    }
                }
            }
            
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (remove[i][j]) {
                        map[i][j] = ' ';
                        count++;
                    }
                }
            }
            
            if (count == 0) {
                break;
            }
            
            answer += count;
            
            for (int j = 0; j < n; j++) {
                int row = m - 1;
                
                for (int i = m - 1; i >= 0; i--) {
                    if (map[i][j] != ' ') {
                        char block = map[i][j];
                        map[i][j] = ' ';
                        map[row][j] = block;
                        row--;
                    }
                }
            }
        }
        
        return answer;
    }
}