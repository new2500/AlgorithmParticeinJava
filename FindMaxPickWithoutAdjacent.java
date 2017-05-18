package airbnb;

/**
 * Created by EricLee on 10/26/16.
 */
public class FindMaxPickWithoutAdjacent {

    public static void main(String[] args) {
        int[] array = {1,2,-3};
        System.out.print(findMaxSubarrayNotAdjacent(array));
    }

    public static int findMaxSubarrayNotAdjacent(int[] array) {
        if (array == null || array.length == 0) return 0;
        int[] pick = new int[array.length];
        int[] notPick = new int[array.length];
        pick[0] = array[0];
        notPick[0] = 0;
        int max = pick[0];
        for (int i = 1; i < array.length; i++) {
            pick[i] = Math.max(0, Math.max(array[i], notPick[i - 1] + array[i]));
            notPick[i] = Math.max(0, Math.max(notPick[i - 1], pick[i - 1]));
            max = Math.max(max, Math.max(pick[i], notPick[i]));
        }
        return max;
    }
}
