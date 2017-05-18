package airbnb;

import java.util.*;

/**
 * Created by EricLee on 10/26/16.
 */
public class PalindromePairs {

    public static void main(String[] args) {
        String[] words = {"a", "bc"};
        List<List<Integer>> result = getPalindromePairs(words);
        for (List<Integer> pair : result) {
            System.out.println("[" + pair.get(0) + ", " + pair.get(1) + "]");
        }
    }

    public static List<List<Integer>> getPalindromePairs(String[] words) {
        if (words == null || words.length == 0) return Collections.emptyList();
        List<List<Integer>> result = new ArrayList<>();
        // Storing word indices
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }

        // Finding palindrome pairs
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            for (int j = 0; j <= word.length(); j++) {
                String prefix = word.substring(0, j);
                String suffix = word.substring(j);
                if (isPalindrome(prefix)) {
                    String reversed = new StringBuilder(suffix).reverse().toString();
                    if (map.containsKey(reversed) && map.get(reversed) != i) {
                        List<Integer> ret = new ArrayList<>();
                        ret.add(map.get(reversed));
                        ret.add(i);
                        result.add(ret);
                    }
                }
                if (isPalindrome(suffix)) {
                    String reversed = new StringBuilder(prefix).reverse().toString();
                    if (map.containsKey(reversed) && map.get(reversed) != i
                            && prefix.length() > suffix.length()) {
                        List<Integer> ret = new ArrayList<>();
                        ret.add(i);
                        ret.add(map.get(reversed));
                        result.add(ret);
                    }
                }
            } // for j
        } // for i
        return result;
    }

    public static boolean isPalindrome(String str) {
        int lo = 0;
        int hi = str.length() - 1;
        while (lo <= hi) {
            if (str.charAt(lo++) != str.charAt(hi--)) return false;
        }
        return true;
    }
}
