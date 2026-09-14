class Solution {
    public int[] solution(int[] arr, int[] delete_list) {
        int[] temp = new int[arr.length];
        int count = 0;

        for (int num : arr) {
            boolean delete = false;

            for (int d : delete_list) {
                if (num == d) {
                    delete = true;
                    break;
                }
            }

            if (!delete) {
                temp[count++] = num;
            }
        }

        int[] answer = new int[count];

        for (int i = 0; i < count; i++) {
            answer[i] = temp[i];
        }

        return answer;
    }
}