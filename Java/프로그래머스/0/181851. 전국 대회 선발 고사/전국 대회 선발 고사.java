class Solution {
    public int solution(int[] rank, boolean[] attendance) {
        int[] selected = new int[3];
        int count = 0;

        for (int r = 1; r <= rank.length && count < 3; r++) {
            for (int i = 0; i < rank.length; i++) {
                if (rank[i] == r && attendance[i]) {
                    selected[count++] = i;
                    break;
                }
            }
        }

        return 10000 * selected[0] + 100 * selected[1] + selected[2];
    }
}