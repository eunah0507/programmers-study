class Solution {
    public int solution(int[] players, int m, int k) {
        
        int answer = 0;
        
        int[] servers = new int[24 + k];

        for (int i = 0; i < 24; i++) {
            int need = players[i] / m;

            if (servers[i] < need) {
                int add = need - servers[i];
                answer += add;

                for (int j = i; j < i + k; j++) {
                    servers[j] += add;
                }
            }
        }
        return answer;
    }
}