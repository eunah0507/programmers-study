import java.util.*;

class Solution {
    public int[] solution(String my_string) {
        int[] count = new int[10];
        int size = 0;

        for (char c : my_string.toCharArray()) {
            if (Character.isDigit(c)) {
                count[c - '0']++;
                size++;
            }
        }

        int[] answer = new int[size];
        int index = 0;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < count[i]; j++) {
                answer[index++] = i;
            }
        }

        return answer;
    }
}