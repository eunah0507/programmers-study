class Solution {
    public int solution(int order) {
        int answer = 0;

        while (order > 0) {
            int number = order % 10;

            if (number == 3 || number == 6 || number == 9) {
                answer++;
            }

            order /= 10;
        }

        return answer;
    }
}