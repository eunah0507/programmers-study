class Solution {
    public String solution(String code) {
        StringBuilder sb = new StringBuilder();
        int mode = 0;

        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == '1') {
                mode = 1 - mode;
            } else if (i % 2 == mode) {
                sb.append(code.charAt(i));
            }
        }

        return sb.length() == 0 ? "EMPTY" : sb.toString();
    }
}