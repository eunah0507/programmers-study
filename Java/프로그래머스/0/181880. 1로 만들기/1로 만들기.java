class Solution {
    public int solution(int[] num_list) {
        int answer = 0;

        for (int num : num_list) {
            while (num > 1) {
                num /= 2;
                answer++;
            }
        }

        return answer;
    }
}