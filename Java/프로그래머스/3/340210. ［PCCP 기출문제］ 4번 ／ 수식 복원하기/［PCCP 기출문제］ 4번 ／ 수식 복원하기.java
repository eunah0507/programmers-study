import java.util.ArrayList;
import java.util.List;

class Solution {
    public String[] solution(String[] expressions) {
        List<Integer> bases = new ArrayList<>();

        for (int base = 2; base <= 9; base++) {
            if (isValidBase(expressions, base)) {
                bases.add(base);
            }
        }

        List<String> answer = new ArrayList<>();

        for (String expression : expressions) {
            String[] parts = expression.split(" ");

            if (!parts[4].equals("X")) {
                continue;
            }

            String result = null;
            boolean same = true;

            for (int base : bases) {
                int left = toDecimal(parts[0], base);
                int right = toDecimal(parts[2], base);
                int value;

                if (parts[1].equals("+")) {
                    value = left + right;
                } else {
                    value = left - right;
                }

                String converted = toBase(value, base);

                if (result == null) {
                    result = converted;
                } else if (!result.equals(converted)) {
                    same = false;
                    break;
                }
            }

            answer.add(parts[0] + " " + parts[1] + " " + parts[2] + " = " + (same ? result : "?"));
        }

        return answer.toArray(new String[0]);
    }

    private boolean isValidBase(String[] expressions, int base) {
        for (String expression : expressions) {
            String[] parts = expression.split(" ");

            if (!isValidNumber(parts[0], base) || !isValidNumber(parts[2], base)) {
                return false;
            }

            if (parts[4].equals("X")) {
                continue;
            }

            if (!isValidNumber(parts[4], base)) {
                return false;
            }

            int left = toDecimal(parts[0], base);
            int right = toDecimal(parts[2], base);
            int expected = toDecimal(parts[4], base);
            int result;

            if (parts[1].equals("+")) {
                result = left + right;
            } else {
                result = left - right;
            }

            if (result != expected) {
                return false;
            }
        }

        return true;
    }

    private boolean isValidNumber(String number, int base) {
        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) - '0' >= base) {
                return false;
            }
        }

        return true;
    }

    private int toDecimal(String number, int base) {
        int result = 0;

        for (int i = 0; i < number.length(); i++) {
            result = result * base + number.charAt(i) - '0';
        }

        return result;
    }

    private String toBase(int number, int base) {
        if (number == 0) {
            return "0";
        }

        StringBuilder result = new StringBuilder();

        while (number > 0) {
            result.append(number % base);
            number /= base;
        }

        return result.reverse().toString();
    }
}