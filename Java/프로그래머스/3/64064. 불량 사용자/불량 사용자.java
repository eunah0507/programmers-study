import java.util.*;

class Solution {
    Set<Integer> set = new HashSet<>();
    
    public int solution(String[] user_id, String[] banned_id) {
        dfs(user_id, banned_id, 0, 0);
        return set.size();
    }
    
    void dfs(String[] user_id, String[] banned_id, int depth, int bit) {
        if (depth == banned_id.length) {
            set.add(bit);
            return;
        }
        
        for (int i = 0; i < user_id.length; i++) {
            if ((bit & (1 << i)) != 0) {
                continue;
            }
            
            if (check(user_id[i], banned_id[depth])) {
                dfs(user_id, banned_id, depth + 1, bit | (1 << i));
            }
        }
    }
    
    boolean check(String user, String banned) {
        if (user.length() != banned.length()) {
            return false;
        }
        
        for (int i = 0; i < user.length(); i++) {
            if (banned.charAt(i) != '*' && user.charAt(i) != banned.charAt(i)) {
                return false;
            }
        }
        
        return true;
    }
}