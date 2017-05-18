package airbnb;

/**
 * Created by EricLee on 11/5/16.
 */
public class EggDropping {

    public static void main(String[] args) {

    }

    public static int minimumAttemps(int floors, int eggs) {
        int[][] dp = new int[floors+1][eggs+1];
        for (int i = 0; i <= floors; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= floors; i++) {
            for (int j = 1; j <= eggs; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                // If the egg breaks, we have i-1 floors and j-1 eggs to work with
                // If te egg doesn't break, we have i-attempt floors and j egges to work with
                for (int attempt = 1; attempt <= i; attempt++) {
                    int min = Math.max(dp[i-1][j-1], dp[i-attempt][j]);
                    dp[i][j] = Math.min(min, dp[i][j]);
                }
            }
        }
        return dp[floors - 1][eggs - 1];
    }
}
