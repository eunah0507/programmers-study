class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        int[] count = new int[26];

        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < count.length; i++) {
            if (count[i] == 1) {
                sb.append((char) (i + 'a'));
            }
        }

        return sb.toString();
    }
}