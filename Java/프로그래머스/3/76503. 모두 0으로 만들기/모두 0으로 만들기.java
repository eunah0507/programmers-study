import java.util.ArrayList;

class Solution {
    public long solution(int[] a, int[][] edges) {
        int n = a.length;
        long sum = 0;

        for (int num : a) {
            sum += num;
        }

        if (sum != 0) {
            return -1;
        }

        ArrayList<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        long[] weight = new long[n];

        for (int i = 0; i < n; i++) {
            weight[i] = a[i];
        }

        int[] parent = new int[n];
        int[] stack = new int[n];
        int[] order = new int[n];
        int top = 0;
        int idx = 0;

        parent[0] = -1;
        stack[top++] = 0;

        while (top > 0) {
            int cur = stack[--top];
            order[idx++] = cur;

            for (int next : graph[cur]) {
                if (next != parent[cur]) {
                    parent[next] = cur;
                    stack[top++] = next;
                }
            }
        }

        long answer = 0;

        for (int i = n - 1; i > 0; i--) {
            int cur = order[i];
            answer += Math.abs(weight[cur]);
            weight[parent[cur]] += weight[cur];
        }

        return answer;
    }
}