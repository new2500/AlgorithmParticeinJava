package airbnb;

/**
 * Created by EricLee on 11/1/16.
 */
public class HilbertCurve {

    public static void main(String[] args) {
        System.out.println(hilbertCurve(1,1,2));
        System.out.println(hilbertCurve(0,1,1));
        System.out.println(hilbertCurve(2,2,2));
        System.out.println(hilbertCurve(1,3,2));
        System.out.println(hilbertCurve(3,1,2));
    }

    public static int hilbertCurve(int x, int y, int iter) {
        if (iter == 0) return 1;
        int len = 1 << (iter - 1);
        int num = 1 << (2 * (iter - 1));

        if (x >= len && y >= len) {
            // 3 Shape is identical with previous iteration
            return 2 * num + hilbertCurve(x - len, y - len, iter - 1);
        } else if (x < len && y >= len) {
            // 2 Shape is identical with previous iteration
            return num + hilbertCurve(x, y - len, iter - 1);
        } else if (x < len && y < len) {
            // 1 Clock-wise rotate 90
            return hilbertCurve(y, x, iter - 1);
        } else {
            // 4 Anti-Clockwise rotate 90
            return 3 * num + hilbertCurve(len - 1 - y, 2 * len - 1 - x, iter - 1);
        }
    }
}
