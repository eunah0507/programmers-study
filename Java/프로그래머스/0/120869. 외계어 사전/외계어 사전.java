class Solution {
    public int solution(String[] spell, String[] dic) {
        for (String word : dic) {
            if (word.length() != spell.length) {
                continue;
            }

            boolean valid = true;
            for (String alphabet : spell) {
                if (!word.contains(alphabet)) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                return 1;
            }
        }

        return 2;
    }
}