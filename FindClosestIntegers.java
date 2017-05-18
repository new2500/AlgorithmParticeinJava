package airbnb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by EricLee on 10/26/16.
 */
public class FindClosestIntegers {

    public static void main(String[] args) {
        double[] prices1 = {2.2,3.9,4.4};
        int[] rounded = rounding(prices1);
        for (int num : rounded) {
            System.out.print(num + " ");
        }
    }

    public static int[] rounding(double[] prices) {
        if (prices == null || prices.length == 0) return new int[0];
        int totalRound = 0;
        int sepRound = 0;
        double totalDouble = 0;
        List<Double> priceList = new ArrayList<>();
        for (double price : prices) {
            totalDouble += price;
            sepRound += (int) Math.round(price);
            priceList.add(price);
        }
        totalRound = (int) Math.round(totalDouble);
        int diff = totalRound - sepRound;
        boolean overflow = diff < 0;
        diff = Math.abs(diff);
        if (overflow) {
            Collections.sort(priceList, new RoundDown());
        } else {
            Collections.sort(priceList, new RoundUp());
        }

        int[] result = new int[prices.length];
        for (int i = 0; i < prices.length; i++) {
            if (i < diff) {
                result[i] = overflow ? (int) Math.floor(priceList.get(i)) : (int) Math.ceil(priceList.get(i));
            } else {
                result[i] = (int) Math.round(priceList.get(i));
            }
        }
        return result;
    }

    static class RoundDown implements Comparator<Double> {
        public int compare(Double d1, Double d2) {
            double floating1 = d1 - Math.floor(d1);
            double floating2 = d2 - Math.floor(d2);
            return Double.compare(floating1, floating2);
        }
    }
    static class RoundUp implements Comparator<Double> {
        public int compare(Double d1, Double d2) {
            double floating1 = d1 - Math.floor(d1);
            double floating2 = d2 - Math.floor(d2);
            return Double.compare(floating2, floating1);
        }
    }
}
