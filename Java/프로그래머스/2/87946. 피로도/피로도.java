class Solution {
    private boolean[] visited;
    private int answer = 0;

    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        dfs(k, dungeons, 0);
        return answer;
    }

    private void dfs(int fatigue, int[][] dungeons, int count) {
        answer = Math.max(answer, count);

        for (int i = 0; i < dungeons.length; i++) {
            if (!visited[i] && fatigue >= dungeons[i][0]) {
                visited[i] = true;
                dfs(fatigue - dungeons[i][1], dungeons, count + 1);
                visited[i] = false;
            }
        }
    }
}