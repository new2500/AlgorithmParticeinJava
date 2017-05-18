package airbnb;

import java.util.ArrayList;

/**
 * Created by EricLee on 10/31/16.
 */
public class KSum {

    public int kSum(int A[], int k, int target) {
        // write your code here
        if (A == null || A.length < k) return 0;
        int[][][] dp = new int[A.length + 1][k + 1][target + 1];
        for (int i = 0; i <= A.length; i++) {
            dp[i][0][0] = 1;
        }
        for (int i = 1; i <= A.length; i++) {
            for (int n = 1; n <= k && n <= i; n++) {
                for (int t = 1; t <= target; t++) {
                    if (A[i-1] <= t) {
                        dp[i][n][t] = dp[i-1][n-1][t-A[i-1]];
                    }
                    dp[i][n][t] += dp[i - 1][n][t];
                }
            }
        }
        return dp[A.length][k][target];
    }

    public ArrayList<ArrayList<Integer>> kSumII(int[] A, int k, int target) {
        // write your code here
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (A == null || A.length == 0 || k > A.length) return result;
        getAllResults(result, A, k, target, 0, new ArrayList<Integer>());
        return result;
    }

    public void getAllResults(ArrayList<ArrayList<Integer>> result, int[] A, int k, int target, int index, ArrayList<Integer> holder) {
        if (holder.size() == k) {
            if (target == 0) result.add(new ArrayList<>(holder));
        } else {
            for (int i = index; i < A.length; i++) {
                holder.add(A[i]);
                getAllResults(result, A, k, target - A[i], i + 1, holder);
                holder.remove(holder.size() - 1);
            }
        }
    }
}
