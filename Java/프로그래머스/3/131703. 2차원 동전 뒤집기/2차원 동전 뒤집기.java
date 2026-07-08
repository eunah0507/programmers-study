class Solution {
    public int solution(int[][] beginning, int[][] target) {
        int n = beginning.length;
        int m = beginning[0].length;
        int answer = Integer.MAX_VALUE;
        
        for (int rowMask = 0; rowMask < (1 << n); rowMask++) {
            int count = Integer.bitCount(rowMask);
            boolean[] colFlip = new boolean[m];
            
            for (int j = 0; j < m; j++) {
                int value = beginning[0][j];
                
                if ((rowMask & 1) != 0) {
                    value ^= 1;
                }
                
                colFlip[j] = value != target[0][j];
                
                if (colFlip[j]) {
                    count++;
                }
            }
            
            boolean possible = true;
            
            for (int i = 1; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    int value = beginning[i][j];
                    
                    if ((rowMask & (1 << i)) != 0) {
                        value ^= 1;
                    }
                    
                    if (colFlip[j]) {
                        value ^= 1;
                    }
                    
                    if (value != target[i][j]) {
                        possible = false;
                        break;
                    }
                }
                
                if (!possible) {
                    break;
                }
            }
            
            if (possible) {
                answer = Math.min(answer, count);
            }
        }
        
        if (answer == Integer.MAX_VALUE) {
            return -1;
        }
        
        return answer;
    }
}