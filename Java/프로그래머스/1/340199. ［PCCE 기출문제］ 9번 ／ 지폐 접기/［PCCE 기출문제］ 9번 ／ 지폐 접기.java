class Solution {
    public int solution(int[] wallet, int[] bill) {
        int walletShort = Math.min(wallet[0], wallet[1]);
        int walletLong = Math.max(wallet[0], wallet[1]);
        int width = bill[0];
        int height = bill[1];
        int answer = 0;

        while (Math.min(width, height) > walletShort
                || Math.max(width, height) > walletLong) {
            if (width > height) {
                width /= 2;
            } else {
                height /= 2;
            }
            answer++;
        }

        return answer;
    }
}