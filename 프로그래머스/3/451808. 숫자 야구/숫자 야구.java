import java.util.function.Function;

class Solution {
    public int solution(int n, Function<Integer, String> submit) {
        int[] candidates = new int[3024];
        int count = 0;

        for (int i = 1234; i <= 9876; i++) {
            if (isValid(i)) {
                candidates[count++] = i;
            }
        }

        while (count > 1) {
            int guess = getBestGuess(candidates, count);
            String result = submit.apply(guess);
            int score = parse(result);

            int nextCount = 0;
            for (int i = 0; i < count; i++) {
                if (getScore(guess, candidates[i]) == score) {
                    candidates[nextCount++] = candidates[i];
                }
            }
            count = nextCount;
        }

        return candidates[0];
    }

    private int getBestGuess(int[] candidates, int count) {
        int bestGuess = candidates[0];
        int bestMax = count;

        for (int guess = 1000; guess <= 9999; guess++) {
            int[] bucket = new int[25];
            int max = 0;

            for (int i = 0; i < count; i++) {
                int score = getScore(guess, candidates[i]);
                bucket[score]++;

                if (bucket[score] > max) {
                    max = bucket[score];
                }

                if (max >= bestMax) {
                    break;
                }
            }

            if (max < bestMax) {
                bestMax = max;
                bestGuess = guess;
            }
        }

        return bestGuess;
    }

    private int getScore(int guess, int answer) {
        int strike = 0;
        int ball = 0;

        int[] guessDigit = new int[4];
        int[] answerDigit = new int[4];

        for (int i = 3; i >= 0; i--) {
            guessDigit[i] = guess % 10;
            answerDigit[i] = answer % 10;
            guess /= 10;
            answer /= 10;
        }

        for (int i = 0; i < 4; i++) {
            if (guessDigit[i] == answerDigit[i]) {
                strike++;
            } else {
                for (int j = 0; j < 4; j++) {
                    if (guessDigit[i] == answerDigit[j]) {
                        ball++;
                        break;
                    }
                }
            }
        }

        return strike * 5 + ball;
    }

    private int parse(String result) {
        int strike = result.charAt(0) - '0';
        int ball = result.charAt(3) - '0';

        return strike * 5 + ball;
    }

    private boolean isValid(int number) {
        boolean[] used = new boolean[10];

        for (int i = 0; i < 4; i++) {
            int digit = number % 10;

            if (digit == 0 || used[digit]) {
                return false;
            }

            used[digit] = true;
            number /= 10;
        }

        return true;
    }
}