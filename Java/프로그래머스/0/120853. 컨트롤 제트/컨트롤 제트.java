class Solution {
    public int solution(String s) {
        int answer = 0;
        int prev = 0;

        for (String str : s.split(" ")) {
            if (str.equals("Z")) {
                answer -= prev;
            } else {
                prev = Integer.parseInt(str);
                answer += prev;
            }
        }

        return answer;
    }
}