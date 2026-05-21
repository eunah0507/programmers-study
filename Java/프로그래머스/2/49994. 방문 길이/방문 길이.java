import java.util.HashSet;
import java.util.Set;

class Solution {
    public int solution(String dirs) {
        Set<String> set = new HashSet<>();
        int x = 0;
        int y = 0;

        for (int i = 0; i < dirs.length(); i++) {
            char dir = dirs.charAt(i);
            int nx = x;
            int ny = y;

            if (dir == 'U') {
                ny++;
            } else if (dir == 'D') {
                ny--;
            } else if (dir == 'R') {
                nx++;
            } else if (dir == 'L') {
                nx--;
            }

            if (nx < -5 || nx > 5 || ny < -5 || ny > 5) {
                continue;
            }

            set.add(x + "," + y + "," + nx + "," + ny);
            set.add(nx + "," + ny + "," + x + "," + y);

            x = nx;
            y = ny;
        }

        return set.size() / 2;
    }
}