class Solution {
    public String solution(String video_len, String pos, String op_start,
                           String op_end, String[] commands) {
        int duration = toSeconds(video_len);
        int position = toSeconds(pos);
        int openingStart = toSeconds(op_start);
        int openingEnd = toSeconds(op_end);

        position = skipOpening(position, openingStart, openingEnd);

        for (String command : commands) {
            if ("prev".equals(command)) {
                position = Math.max(0, position - 10);
            } else {
                position = Math.min(duration, position + 10);
            }

            position = skipOpening(position, openingStart, openingEnd);
        }

        return String.format(java.util.Locale.ROOT, "%02d:%02d", position / 60, position % 60);
    }

    private int toSeconds(String time) {
        return Integer.parseInt(time.substring(0, 2)) * 60
                + Integer.parseInt(time.substring(3));
    }

    private int skipOpening(int position, int openingStart, int openingEnd) {
        if (openingStart <= position && position <= openingEnd) {
            return openingEnd;
        }

        return position;
    }
}