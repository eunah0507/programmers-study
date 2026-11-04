class Solution {
    public int[] solution(int[][] score) {
        int[] answer = new int[score.length];

        for (int i = 0; i < score.length; i++) {
            int rank = 1;
            int sum = score[i][0] + score[i][1];

            for (int j = 0; j < score.length; j++) {
                if (sum < score[j][0] + score[j][1]) {
                    rank++;
                }
            }

            answer[i] = rank;
        }

        return answer;
    }
}