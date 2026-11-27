class Solution {
    public int solution(int[] arr) {
        int answer = 0;

        for (int num : arr) {
            int count = 0;

            while (true) {
                if (num >= 50 && num % 2 == 0) {
                    num /= 2;
                } else if (num < 50 && num % 2 != 0) {
                    num = num * 2 + 1;
                } else {
                    break;
                }

                count++;
            }

            answer = Math.max(answer, count);
        }

        return answer;
    }
}