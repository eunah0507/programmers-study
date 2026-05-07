class Solution {
    public int[] solution(int n, int s) {
        if (s < n) {
            return new int[]{-1};
        }
        
        int[] answer = new int[n];
        int num = s / n;
        int remain = s % n;
        
        for (int i = 0; i < n; i++) {
            answer[i] = num;
        }
        
        for (int i = n - remain; i < n; i++) {
            answer[i]++;
        }
        
        return answer;
    }
}