import java.util.*;

class Solution {
    public String[] solution(String myStr) {
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < myStr.length(); i++) {
            char ch = myStr.charAt(i);

            if (ch == 'a' || ch == 'b' || ch == 'c') {
                if (sb.length() > 0) {
                    list.add(sb.toString());
                    sb.setLength(0);
                }
            } else {
                sb.append(ch);
            }
        }

        if (sb.length() > 0) {
            list.add(sb.toString());
        }

        if (list.isEmpty()) {
            return new String[]{"EMPTY"};
        }

        return list.toArray(new String[0]);
    }
}