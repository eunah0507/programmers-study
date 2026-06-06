import java.util.*;

class Solution {
    static boolean[][] pillar;
    static boolean[][] beam;
    static int size;
    
    public int[][] solution(int n, int[][] build_frame) {
        size = n;
        pillar = new boolean[n + 1][n + 1];
        beam = new boolean[n + 1][n + 1];
        
        for (int[] frame : build_frame) {
            int x = frame[0];
            int y = frame[1];
            int a = frame[2];
            int b = frame[3];
            
            if (b == 1) {
                if (a == 0) {
                    pillar[x][y] = true;
                    if (!isValid()) {
                        pillar[x][y] = false;
                    }
                } else {
                    beam[x][y] = true;
                    if (!isValid()) {
                        beam[x][y] = false;
                    }
                }
            } else {
                if (a == 0) {
                    pillar[x][y] = false;
                    if (!isValid()) {
                        pillar[x][y] = true;
                    }
                } else {
                    beam[x][y] = false;
                    if (!isValid()) {
                        beam[x][y] = true;
                    }
                }
            }
        }
        
        ArrayList<int[]> list = new ArrayList<>();
        
        for (int x = 0; x <= n; x++) {
            for (int y = 0; y <= n; y++) {
                if (pillar[x][y]) {
                    list.add(new int[] {x, y, 0});
                }
                
                if (beam[x][y]) {
                    list.add(new int[] {x, y, 1});
                }
            }
        }
        
        int[][] answer = new int[list.size()][3];
        
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
    
    static boolean isValid() {
        for (int x = 0; x <= size; x++) {
            for (int y = 0; y <= size; y++) {
                if (pillar[x][y] && !canPillar(x, y)) {
                    return false;
                }
                
                if (beam[x][y] && !canBeam(x, y)) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    static boolean canPillar(int x, int y) {
        if (y == 0) {
            return true;
        }
        
        if (pillar[x][y - 1]) {
            return true;
        }
        
        if (x > 0 && beam[x - 1][y]) {
            return true;
        }
        
        return beam[x][y];
    }
    
    static boolean canBeam(int x, int y) {
        if (y > 0 && pillar[x][y - 1]) {
            return true;
        }
        
        if (x < size && y > 0 && pillar[x + 1][y - 1]) {
            return true;
        }
        
        if (x > 0 && x < size && beam[x - 1][y] && beam[x + 1][y]) {
            return true;
        }
        
        return false;
    }
}