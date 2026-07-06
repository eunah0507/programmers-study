class Solution {
    public int solution(int n, int[][] q, int[] ans) {
        int answer = 0;

        for (int a = 1; a <= n - 4; a++) {
            for (int b = a + 1; b <= n - 3; b++) {
                for (int c = b + 1; c <= n - 2; c++) {
                    for (int d = c + 1; d <= n - 1; d++) {
                        for (int e = d + 1; e <= n; e++) {
                            if (isValid(new int[]{a, b, c, d, e}, q, ans)) {
                                answer++;
                            }
                        }
                    }
                }
            }
        }

        return answer;
    }

    private boolean isValid(int[] code, int[][] q, int[] ans) {
        for (int i = 0; i < q.length; i++) {
            int count = 0;

            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if (code[j] == q[i][k]) {
                        count++;
                    }
                }
            }

            if (count != ans[i]) {
                return false;
            }
        }

        return true;
    }
}