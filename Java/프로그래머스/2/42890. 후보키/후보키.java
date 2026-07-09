import java.util.*;

class Solution {
    public int solution(String[][] relation) {
        int row = relation.length;
        int col = relation[0].length;
        ArrayList<Integer> keys = new ArrayList<>();

        for (int mask = 1; mask < (1 << col); mask++) {
            boolean minimal = true;

            for (int key : keys) {
                if ((mask & key) == key) {
                    minimal = false;
                    break;
                }
            }

            if (!minimal) {
                continue;
            }

            HashSet<String> set = new HashSet<>();

            for (int i = 0; i < row; i++) {
                StringBuilder sb = new StringBuilder();

                for (int j = 0; j < col; j++) {
                    if ((mask & (1 << j)) != 0) {
                        sb.append(relation[i][j]).append("|");
                    }
                }

                set.add(sb.toString());
            }

            if (set.size() == row) {
                keys.add(mask);
            }
        }

        return keys.size();
    }
}