class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int maxHealth = health;
        int time = 0;

        for (int[] attack : attacks) {
            int healTime = attack[0] - time - 1;

            health += healTime * bandage[1];
            health += (healTime / bandage[0]) * bandage[2];
            health = Math.min(health, maxHealth);

            health -= attack[1];

            if (health <= 0) {
                return -1;
            }

            time = attack[0];
        }

        return health;
    }
}