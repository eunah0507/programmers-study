import java.util.ArrayList;

class Solution {
    public int[] solution(int n) {
        ArrayList<Integer> factors = new ArrayList<>();

        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                factors.add(i);
                while (n % i == 0) {
                    n /= i;
                }
            }
        }

        if (n > 1) {
            factors.add(n);
        }

        int[] answer = new int[factors.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = factors.get(i);
        }

        return answer;
    }
}