class Solution {
    public int solution(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            return arr1.length > arr2.length ? 1 : -1;
        }

        int sum1 = 0;
        int sum2 = 0;

        for (int num : arr1) {
            sum1 += num;
        }

        for (int num : arr2) {
            sum2 += num;
        }

        if (sum1 > sum2) {
            return 1;
        }

        if (sum1 < sum2) {
            return -1;
        }

        return 0;
    }
}