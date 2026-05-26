class Solution {

    public String solution(int a, int b) {
        String[] days = {"FRI", "SAT", "SUN", "MON", "TUE", "WED", "THU"};
        int[] month = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        int total = b - 1;

        for (int i = 0; i < a - 1; i++) {
            total += month[i];
        }

        return days[total % 7];
    }
}