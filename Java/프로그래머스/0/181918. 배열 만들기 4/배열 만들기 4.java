import java.util.Arrays;

class Solution {
    public int[] solution(int[] arr) {
        int[] stk = new int[arr.length];
        int size = 0;

        for (int num : arr) {
            while (size > 0 && stk[size - 1] >= num) {
                size--;
            }
            stk[size++] = num;
        }

        return Arrays.copyOf(stk, size);
    }
}