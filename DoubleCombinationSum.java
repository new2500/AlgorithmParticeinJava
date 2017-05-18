package airbnb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by EricLee on 10/27/16.
 */
public class DoubleCombinationSum {

    private static final float epsilon = 1e-6f;

    class Dish {
        String name;
        float price;
        public Dish(String name, float price) {
            this.name = name;
            this.price = price;
        }
    }

    public static void main(String[] args) {
        float[] prices = {10.02f, 0.01f, 0.01f, 1.11f,2.22f,
                3.01f,4.02f, 7.02f, 2.00f,5.03f, 7.01f};
        List<List<Float>> combinations = getCombos(prices, 7.03);
        for (List<Float> oneCombo : combinations) {
            for (float f : oneCombo) {
                System.out.print(f + " ");
            }
            System.out.println("");
        }
    }

    public static List<List<Float>> getCombos(float[] prices, double target) {
        if (prices == null || prices.length == 0) return Collections.emptyList();
        List<List<Float>> combos = new ArrayList<>();
        getAllCombos(combos, prices, target, new ArrayList<Float>(), 0);
        return combos;
    }

    public static void getAllCombos(List<List<Float>> result, float[] prices, double target,
                                    List<Float> currentCombo, int index) {
        if (Math.abs(target) <= epsilon) {
            result.add(new ArrayList<>(currentCombo));
        } else {
            for (int i = index; i < prices.length; i++) {
                currentCombo.add(prices[i]);
                getAllCombos(result, prices, target - prices[i], currentCombo, i + 1);
                currentCombo.remove(currentCombo.size() - 1);
            }
        }
    }
}
