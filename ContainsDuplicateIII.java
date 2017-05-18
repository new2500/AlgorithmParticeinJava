package airbnb;

import java.util.TreeSet;

/**
 * Created by EricLee on 10/26/16.
 */
public class ContainsDuplicateIII {

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || k < 1 || t < 0) return false;
        TreeSet<Long> set = new TreeSet<>();
        long diff = (long)Math.abs(t);
        for (int i = 0; i < nums.length; i++) {
            if (i > k) set.remove((long)(nums[i - k - 1]));
            long upperBound = (long)nums[i] + diff;
            long lowerBound = (long)nums[i] - diff;
            Long floor = set.floor(upperBound);
            Long ceil = set.ceiling(lowerBound);
            if ((floor != null && floor >= lowerBound) || (ceil != null && ceil <= upperBound)) return true;
            set.add((long)nums[i]);
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
