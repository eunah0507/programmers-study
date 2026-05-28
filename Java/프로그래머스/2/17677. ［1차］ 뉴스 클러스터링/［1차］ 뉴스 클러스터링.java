class Solution {
    public int solution(String str1, String str2) {
        int[] first = makeSet(str1);
        int[] second = makeSet(str2);
        int intersection = 0;
        int union = 0;

        for (int i = 0; i < first.length; i++) {
            intersection += Math.min(first[i], second[i]);
            union += Math.max(first[i], second[i]);
        }

        if (union == 0) {
            return 65536;
        }

        return intersection * 65536 / union;
    }

    private int[] makeSet(String str) {
        int[] set = new int[26 * 26];
        str = str.toLowerCase();

        for (int i = 0; i < str.length() - 1; i++) {
            char first = str.charAt(i);
            char second = str.charAt(i + 1);

            if ('a' <= first && first <= 'z' && 'a' <= second && second <= 'z') {
                int index = (first - 'a') * 26 + (second - 'a');
                set[index]++;
            }
        }

        return set;
    }
}