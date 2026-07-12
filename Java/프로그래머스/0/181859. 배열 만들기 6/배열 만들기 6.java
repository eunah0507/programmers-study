import java.util.Arrays;

class Solution {
    public int[] solution(int[] arr) {
        int[] stk = new int[arr.length];
        int size = 0;

        for (int value : arr) {
            if (size > 0 && stk[size - 1] == value) {
                size--;
            } else {
                stk[size++] = value;
            }
        }

        if (size == 0) {
            return new int[]{-1};
        }

        return Arrays.copyOf(stk, size);
    }
}