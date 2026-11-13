class Solution {
    public long solution(int r1, int r2) {
        long innerSquared = (long) r1 * r1;
        long outerSquared = (long) r2 * r2;
        long count = 0;

        for (long x = 1; x <= r2; x++) {
            long xSquared = x * x;
            long minY = (long) Math.ceil(Math.sqrt(Math.max(0L, innerSquared - xSquared)));
            long maxY = (long) Math.sqrt(outerSquared - xSquared);

            count += maxY - minY + 1;
        }

        return count * 4;
    }
}