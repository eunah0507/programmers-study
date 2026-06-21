import java.util.*;

class Solution {
    public long solution(int[][] land, int P, int Q) {
        int n = land.length;
        int size = n * n;
        long[] arr = new long[size];
        long total = 0;
        int idx = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[idx++] = land[i][j];
                total += land[i][j];
            }
        }

        Arrays.sort(arr);

        long answer = Long.MAX_VALUE;
        long prefix = 0;

        for (int i = 0; i < size; i++) {
            long height = arr[i];

            long add = height * i - prefix;
            long remove = total - prefix - height * (size - i);

            long cost = add * P + remove * Q;
            answer = Math.min(answer, cost);

            prefix += height;
        }

        return answer;
    }
}