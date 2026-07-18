import java.util.Arrays;

class Solution {
    public int solution(int[] mats, String[][] park) {
        Arrays.sort(mats);

        for (int i = mats.length - 1; i >= 0; i--) {
            int size = mats[i];

            for (int row = 0; row <= park.length - size; row++) {
                for (int col = 0; col <= park[0].length - size; col++) {
                    boolean canPlace = true;

                    for (int r = row; r < row + size; r++) {
                        for (int c = col; c < col + size; c++) {
                            if (!park[r][c].equals("-1")) {
                                canPlace = false;
                                break;
                            }
                        }

                        if (!canPlace) {
                            break;
                        }
                    }

                    if (canPlace) {
                        return size;
                    }
                }
            }
        }

        return -1;
    }
}