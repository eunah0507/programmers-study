class Solution {
    public int solution(int i, int j, int k) {
        int answer = 0;

        for (int n = i; n <= j; n++) {
            String number = String.valueOf(n);

            for (int l = 0; l < number.length(); l++) {
                if (number.charAt(l) - '0' == k) {
                    answer++;
                }
            }
        }

        return answer;
    }
}