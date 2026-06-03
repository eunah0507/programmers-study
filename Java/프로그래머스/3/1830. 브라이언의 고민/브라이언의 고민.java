import java.util.*;

class Solution {
    String sentence;
    int length;
    String answer;

    public String solution(String sentence) {
        this.sentence = sentence;
        this.length = sentence.length();
        this.answer = null;

        dfs(0, 0, new ArrayList<>());

        if (answer == null) {
            return "invalid";
        }

        return answer;
    }

    void dfs(int index, int used, ArrayList<String> words) {
        if (answer != null) {
            return;
        }

        if (index == length) {
            answer = String.join(" ", words);
            return;
        }

        for (Candidate candidate : getCandidates(index, used)) {
            words.add(candidate.word);
            dfs(candidate.next, used | candidate.mask, words);
            words.remove(words.size() - 1);
        }
    }

    ArrayList<Candidate> getCandidates(int index, int used) {
        ArrayList<Candidate> candidates = new ArrayList<>();

        if (isLower(sentence.charAt(index))) {
            Candidate candidate = parseRuleTwo(index, used);

            if (candidate != null) {
                candidates.add(candidate);
            }

            return candidates;
        }

        Candidate ruleOne = parseRuleOne(index, used);

        if (ruleOne != null) {
            candidates.add(ruleOne);
        }

        int end = index;

        while (end < length && isUpper(sentence.charAt(end))) {
            end++;
        }

        for (int i = end; i > index; i--) {
            candidates.add(new Candidate(i, sentence.substring(index, i), 0));
        }

        return candidates;
    }

    Candidate parseRuleTwo(int start, int used) {
        char outer = sentence.charAt(start);
        int outerBit = 1 << (outer - 'a');

        if ((used & outerBit) != 0) {
            return null;
        }

        int end = -1;
        int count = 0;

        for (int i = start; i < length; i++) {
            if (sentence.charAt(i) == outer) {
                count++;
                end = i;
            }
        }

        if (count != 2 || end == start + 1) {
            return null;
        }

        String inner = sentence.substring(start + 1, end);

        if (isAllUpper(inner)) {
            return new Candidate(end + 1, inner, outerBit);
        }

        Candidate innerRuleOne = parseInnerRuleOne(inner, used | outerBit);

        if (innerRuleOne == null) {
            return null;
        }

        return new Candidate(end + 1, innerRuleOne.word, outerBit | innerRuleOne.mask);
    }

    Candidate parseRuleOne(int start, int used) {
        if (start + 2 >= length || !isUpper(sentence.charAt(start)) || !isLower(sentence.charAt(start + 1))) {
            return null;
        }

        char mark = sentence.charAt(start + 1);
        int bit = 1 << (mark - 'a');

        if ((used & bit) != 0) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(sentence.charAt(start));

        int index = start + 1;

        while (index < length && sentence.charAt(index) == mark) {
            if (index + 1 >= length || !isUpper(sentence.charAt(index + 1))) {
                return null;
            }

            sb.append(sentence.charAt(index + 1));
            index += 2;
        }

        if (sb.length() < 2) {
            return null;
        }

        return new Candidate(index, sb.toString(), bit);
    }

    Candidate parseInnerRuleOne(String str, int used) {
        if (str.length() < 3 || str.length() % 2 == 0) {
            return null;
        }

        if (!isUpper(str.charAt(0)) || !isLower(str.charAt(1))) {
            return null;
        }

        char mark = str.charAt(1);
        int bit = 1 << (mark - 'a');

        if ((used & bit) != 0) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(str.charAt(0));

        for (int i = 1; i < str.length(); i += 2) {
            if (str.charAt(i) != mark || i + 1 >= str.length() || !isUpper(str.charAt(i + 1))) {
                return null;
            }

            sb.append(str.charAt(i + 1));
        }

        return new Candidate(str.length(), sb.toString(), bit);
    }

    boolean isAllUpper(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!isUpper(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    boolean isUpper(char c) {
        return 'A' <= c && c <= 'Z';
    }

    boolean isLower(char c) {
        return 'a' <= c && c <= 'z';
    }

    class Candidate {
        int next;
        String word;
        int mask;

        Candidate(int next, String word, int mask) {
            this.next = next;
            this.word = word;
            this.mask = mask;
        }
    }
}