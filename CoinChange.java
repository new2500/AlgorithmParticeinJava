package airbnb;

import java.util.Arrays;

/**
 * Created by EricLee on 10/29/16.
 */
public class CoinChange {

    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount <= 0) return 0;
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            int minCoins = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                if (i == coins[j]) {
                    minCoins = 1;
                    dp[i] = 1;
                    break;
                } else if (i > coins[j] && dp[i - coins[j]] != -1) {
                    minCoins = Math.min(minCoins, dp[i - coins[j]] + 1);
                }
            }
            dp[i] = minCoins == Integer.MAX_VALUE ? -1 : minCoins;
        }
        return dp[amount];
    }
}
