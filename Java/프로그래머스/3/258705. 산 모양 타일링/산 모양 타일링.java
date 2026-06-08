class Solution {
    public int solution(int n, int[] tops) {
        int mod = 10007;
        int a = 1;
        int b = 0;

        for (int i = 0; i < n; i++) {
            int nextA;
            int nextB = (a + b) % mod;

            if (tops[i] == 1) {
                nextA = (a * 3 + b * 2) % mod;
            } else {
                nextA = (a * 2 + b) % mod;
            }

            a = nextA;
            b = nextB;
        }

        return (a + b) % mod;
    }
}