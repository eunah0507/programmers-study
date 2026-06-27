import java.util.*;

class Solution {
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();

        for (int[] route : routes) {
            int time = 0;
            int r = points[route[0] - 1][0];
            int c = points[route[0] - 1][1];

            add(map, time, r, c);

            for (int i = 1; i < route.length; i++) {
                int nr = points[route[i] - 1][0];
                int nc = points[route[i] - 1][1];

                while (r != nr) {
                    if (r < nr) {
                        r++;
                    } else {
                        r--;
                    }

                    time++;
                    add(map, time, r, c);
                }

                while (c != nc) {
                    if (c < nc) {
                        c++;
                    } else {
                        c--;
                    }

                    time++;
                    add(map, time, r, c);
                }
            }
        }

        for (Map<Integer, Integer> positions : map.values()) {
            for (int count : positions.values()) {
                if (count >= 2) {
                    answer++;
                }
            }
        }

        return answer;
    }

    private void add(Map<Integer, Map<Integer, Integer>> map, int time, int r, int c) {
        map.putIfAbsent(time, new HashMap<>());
        int key = r * 101 + c;
        map.get(time).put(key, map.get(time).getOrDefault(key, 0) + 1);
    }
}