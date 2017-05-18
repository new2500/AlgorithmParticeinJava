package airbnb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by EricLee on 10/30/16.
 */
public class MenuFloat {
    public static void main(String[] args) {
        float[] prices = {10.02f,1.11f,2.22f,2.02f,4.02f,2.00f,5.03f, 3.02f,7.02f};
        List<List<Float>> combinations = getCombinations(prices, 6.04);
        for (List<Float> oneCombo : combinations) {
            for (float d : oneCombo) {
                System.out.print(d + " ");
            }
            System.out.println("");
        }
    }

    public static List<List<Float>> getCombinations(float[] prices, double target) {
        if (prices == null || prices.length == 0 || target < 0) return Collections.emptyList();
        int[] priceInCents = new int[prices.length];
        int targetInCents = (int) Math.round((target * 100));
        List<List<Float>> result = new ArrayList<>();
        Arrays.sort(prices);
        for (int i = 0; i < prices.length; i++) {
            priceInCents[i] = Math.round((prices[i] * 100));
        }
        backtracking(result, prices, priceInCents, targetInCents, 0, new ArrayList<Float>());
        return result;
    }

    public static void backtracking(List<List<Float>> result, float[] prices,
                                    int[] priceInCents, int target, int index, List<Float> path) {
        if (target == 0) {
            result.add(new ArrayList<>(path));
        } else {
            for (int i = index; i < priceInCents.length; i++) {
                if (target < priceInCents[i]) continue;
                path.add(prices[i]);
                backtracking(result, prices, priceInCents, target - priceInCents[i], i, path);
                path.remove(path.size() - 1);
            }
        }
    }
}
