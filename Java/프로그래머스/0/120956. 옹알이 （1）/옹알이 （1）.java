class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        String[] words = {"aya", "ye", "woo", "ma"};

        for (String babble : babbling) {
            for (String word : words) {
                babble = babble.replace(word, " ");
            }

            if (babble.trim().isEmpty()) {
                answer++;
            }
        }

        return answer;
    }
}