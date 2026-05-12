import java.util.*;

class Solution {
    public String solution(long n, String[] bans) {
        long[] pow = new long[12];
        pow[0] = 1;

        for (int i = 1; i <= 11; i++) {
            pow[i] = pow[i - 1] * 26;
        }

        long[] banRanks = new long[bans.length];

        for (int i = 0; i < bans.length; i++) {
            banRanks[i] = getRank(bans[i], pow);
        }

        Arrays.sort(banRanks);

        long left = 1;
        long right = n + bans.length;

        while (left < right) {
            long mid = (left + right) / 2;
            long count = mid - upperBound(banRanks, mid);

            if (count >= n) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return getSpell(left, pow);
    }

    private long getRank(String word, long[] pow) {
        long rank = 0;

        for (int i = 1; i < word.length(); i++) {
            rank += pow[i];
        }

        long index = 0;

        for (int i = 0; i < word.length(); i++) {
            index = index * 26 + (word.charAt(i) - 'a');
        }

        return rank + index + 1;
    }

    private int upperBound(long[] arr, long target) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = (left + right) / 2;

            if (arr[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    private String getSpell(long rank, long[] pow) {
        int len = 1;

        while (rank > pow[len]) {
            rank -= pow[len];
            len++;
        }

        long index = rank - 1;
        char[] result = new char[len];

        for (int i = len - 1; i >= 0; i--) {
            result[i] = (char) ('a' + index % 26);
            index /= 26;
        }

        return new String(result);
    }
}