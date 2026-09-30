class Solution {
    public int[] solution(int[] arr) {
        int length = 0;

        for (int a : arr) {
            length += a;
        }

        int[] answer = new int[length];
        int index = 0;

        for (int a : arr) {
            for (int i = 0; i < a; i++) {
                answer[index++] = a;
            }
        }

        return answer;
    }
}