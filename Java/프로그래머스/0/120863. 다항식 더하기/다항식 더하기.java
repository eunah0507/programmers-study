class Solution {
    public String solution(String polynomial) {
        int coefficient = 0;
        int constant = 0;

        for (String term : polynomial.split(" \\+ ")) {
            if (term.endsWith("x")) {
                coefficient += term.equals("x") ? 1
                        : Integer.parseInt(term.substring(0, term.length() - 1));
            } else {
                constant += Integer.parseInt(term);
            }
        }

        StringBuilder answer = new StringBuilder();

        if (coefficient > 0) {
            if (coefficient > 1) {
                answer.append(coefficient);
            }
            answer.append("x");
        }

        if (constant > 0) {
            if (answer.length() > 0) {
                answer.append(" + ");
            }
            answer.append(constant);
        }

        return answer.toString();
    }
}