class Solution {
    public int solution(String[] strArr) {
        int[] count = new int[31];
        int answer = 0;

        for (String str : strArr) {
            count[str.length()]++;
            answer = Math.max(answer, count[str.length()]);
        }

        return answer;
    }
}