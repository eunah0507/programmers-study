import java.util.*;

class Solution {
    public int solution(int coin, int[] cards) {
        int n = cards.length;
        int target = n + 1;
        int idx = n / 3;
        int answer = 1;
        
        Set<Integer> hand = new HashSet<>();
        Set<Integer> wait = new HashSet<>();
        
        for (int i = 0; i < idx; i++) {
            hand.add(cards[i]);
        }
        
        while (idx < n) {
            wait.add(cards[idx++]);
            wait.add(cards[idx++]);
            
            if (removePair(hand, hand, target)) {
                answer++;
            } else if (coin >= 1 && removePair(hand, wait, target)) {
                coin--;
                answer++;
            } else if (coin >= 2 && removePair(wait, wait, target)) {
                coin -= 2;
                answer++;
            } else {
                break;
            }
        }
        
        return answer;
    }
    
    private boolean removePair(Set<Integer> a, Set<Integer> b, int target) {
        for (int num : new HashSet<>(a)) {
            int pair = target - num;
            
            if (a == b) {
                if (num != pair && a.contains(pair)) {
                    a.remove(num);
                    a.remove(pair);
                    return true;
                }
            } else {
                if (b.contains(pair)) {
                    a.remove(num);
                    b.remove(pair);
                    return true;
                }
            }
        }
        
        return false;
    }
}