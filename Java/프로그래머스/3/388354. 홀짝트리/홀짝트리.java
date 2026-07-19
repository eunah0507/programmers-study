class Solution {
    public int[] solution(int[] nodes, int[][] edges) {
        int n = nodes.length;
        int[] index = new int[1_000_001];
        int[] parent = new int[n];
        int[] degree = new int[n];

        for (int i = 0; i < n; i++) {
            index[nodes[i]] = i + 1;
            parent[i] = i;
        }

        for (int[] edge : edges) {
            int a = index[edge[0]] - 1;
            int b = index[edge[1]] - 1;

            degree[a]++;
            degree[b]++;
            union(parent, a, b);
        }

        int[] sameCount = new int[n];
        int[] differentCount = new int[n];

        for (int i = 0; i < n; i++) {
            int root = find(parent, i);

            if ((nodes[i] & 1) == (degree[i] & 1)) {
                sameCount[root]++;
            } else {
                differentCount[root]++;
            }
        }

        int oddEvenTreeCount = 0;
        int reverseOddEvenTreeCount = 0;

        for (int i = 0; i < n; i++) {
            if (find(parent, i) != i) {
                continue;
            }

            if (sameCount[i] == 1) {
                oddEvenTreeCount++;
            }

            if (differentCount[i] == 1) {
                reverseOddEvenTreeCount++;
            }
        }

        return new int[]{oddEvenTreeCount, reverseOddEvenTreeCount};
    }

    private int find(int[] parent, int node) {
        while (parent[node] != node) {
            parent[node] = parent[parent[node]];
            node = parent[node];
        }

        return node;
    }

    private void union(int[] parent, int a, int b) {
        int rootA = find(parent, a);
        int rootB = find(parent, b);

        if (rootA != rootB) {
            parent[rootB] = rootA;
        }
    }
}