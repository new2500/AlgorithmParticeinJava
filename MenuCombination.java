package airbnb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by EricLee on 10/30/16.
 */
public class MenuCombination {

    public static void main(String[] args) {
        double[] prices = {10.02,1.11,2.22,3.01,4.02,2.00,5.03};
        List<List<Double>> combinations = getCombinations(prices, 7.03);
        for (List<Double> oneCombo : combinations) {
            for (double d : oneCombo) {
                System.out.print(d + " ");
            }
            System.out.println("");
        }
    }

    public static List<List<Double>> getCombinations(double[] prices, double target) {
        if (prices == null || prices.length == 0 || target < 0) return Collections.emptyList();
        int[] priceInCents = new int[prices.length];
        int targetInCents = (int) Math.round((target * 100));
        List<List<Double>> result = new ArrayList<>();
        Arrays.sort(prices);
        for (int i = 0; i < prices.length; i++) {
            priceInCents[i] = (int) Math.round((prices[i] * 100));
        }
        backtracking(result, prices, priceInCents, targetInCents, 0, new ArrayList<Double>());
        return result;
    }

    public static void backtracking(List<List<Double>> result, double[] prices,
                                    int[] priceInCents, int target, int index, List<Double> path) {
        if (target == 0) {
            result.add(new ArrayList<>(path));
        } else {
            for (int i = index; i < priceInCents.length; i++) {
                if ((i > index && priceInCents[i] == priceInCents[i - 1])|| target < priceInCents[i]) continue;
                path.add(prices[i]);
                backtracking(result, prices, priceInCents, target - priceInCents[i], i + 1, path);
                path.remove(path.size() - 1);
            }
        }
    }
}
