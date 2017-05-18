package airbnb;

import java.util.Arrays;

/**
 * Created by EricLee on 10/30/16.
 */
public class GuessMachine {

    public static void main(String[] args) {
        System.out.println(guessMachine());
    }

    public static int guessResult(int num) {
        int count = 0;
        int base = 1000;
        int ret = 2223;
        while (base != 0) {
            if (num / base == ret / base) count++;
            num %= base;
            ret %= base;
            base /= 10;
        }
        return count;
    }

    public static int guessMachine() {
        int[] test = {1000, 100, 10, 1};
        int[] result = new int[4];
        int totalCorrection = 0;
        Arrays.fill(result, -1);
        int base = 1;
        while (totalCorrection < 4) {
            int correct = guessResult(base * 1111);
            int j = 0;
            while (j < test.length && correct > 0) {
                if (result[j] != -1) {
                    j++;
                    continue;
                }
                int attemp = test[j] * base;
                if (guessResult(attemp) != 0) {
                    result[j] = base;
                    correct--;
                    totalCorrection++;
                }
                j++;
            }
            base++;
        }

        int sum = 0;
        for (int i = 0; i < test.length; i++) {
            sum += result[i] * test[i];
        }
        return sum;
    }
}
