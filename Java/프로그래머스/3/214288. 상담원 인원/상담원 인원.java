import java.util.PriorityQueue;

class Solution {
    public int solution(int k, int n, int[][] reqs) {
        int[][] wait = new int[k + 1][n + 1];

        for (int type = 1; type <= k; type++) {
            for (int cnt = 1; cnt <= n; cnt++) {
                wait[type][cnt] = getWaitTime(type, cnt, reqs);
            }
        }

        return dfs(1, n, k, wait);
    }

    private int dfs(int type, int remain, int k, int[][] wait) {
        if (type == k) {
            return wait[type][remain];
        }

        int min = Integer.MAX_VALUE;

        for (int cnt = 1; cnt <= remain - (k - type); cnt++) {
            min = Math.min(min, wait[type][cnt] + dfs(type + 1, remain - cnt, k, wait));
        }

        return min;
    }

    private int getWaitTime(int type, int cnt, int[][] reqs) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < cnt; i++) {
            pq.offer(0);
        }

        int total = 0;

        for (int[] req : reqs) {
            if (req[2] != type) {
                continue;
            }

            int start = req[0];
            int time = req[1];
            int end = pq.poll();

            if (end <= start) {
                pq.offer(start + time);
            } else {
                total += end - start;
                pq.offer(end + time);
            }
        }

        return total;
    }
}