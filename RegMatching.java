package airbnb;

/**
 * Created by EricLee on 10/29/16.
 */
public class RegMatching {

    public static void main(String[] args) {
        String pattern = "s+a*";
        String source = "saaaa";
        System.out.print(regMatchDP(source, pattern));
    }

    public static boolean regMatch(String source, String pattern) {
        if (pattern.length() == 0) return source.length() == 0;
        if (pattern.length() == 1) {
            if (source.length() > 1 || source.length() == 0) return false;
            return source.charAt(0) == pattern.charAt(0);
        }

        if (source.length() != 0 && (pattern.charAt(0) == '.' || pattern.charAt(0) == source.charAt(0))) {
            if (pattern.charAt(1) == '*') {
                return regMatch(source.substring(1), pattern) || regMatch(source, pattern.substring(2));
            } else if (pattern.charAt(1) == '+') {
                return regMatch(source.substring(1), pattern.substring(2)) || regMatch(source.substring(1), pattern.substring(2));
            } else {
                return regMatch(source.substring(1), pattern.substring(1));
            }
        }
        return pattern.charAt(1) == '*' && regMatch(source, pattern.substring(2));
    }

    public static boolean regMatchDP(String s, String p) {
        if (s == null || p == null) return false;
        boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i <= p.length(); i++) {
            if (p.charAt(i - 1) == '*' && dp[i - 2][0]) dp[i][0] = true;
        }

        // Start reg match
        for (int i = 1; i <= p.length(); i++) {
            for (int j = 1; j <= s.length(); j++) {
                // If '.' or identical char
                if (p.charAt(i - 1) == '.' || p.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(i - 1) == '*' || p.charAt(i - 1) == '+') {
                    // If the pattern is * OR +, we decide based on the previous character
                    if (p.charAt(i - 2) == '.' || p.charAt(i - 2) == s.charAt(j - 1)) {
                        // If there's a match, the star could represent 0, 1 or more repetance
                        // the plus should represent 1 or more
                        if (p.charAt(i - 1) == '*') {
                            dp[i][j] = dp[i - 2][j] || dp[i - 2][j - 1] || dp[i][j - 1];
                        } else {
                            dp[i][j] = dp[i - 2][j - 1] || dp[i][j - 1];
                        }
                    } else {
                        // Otherwise, + means doesn't match since a+ never match c
                        // If its a * then it can only means 0 => c*a matches a
                        dp[i][j] = p.charAt(i - 1) == '*' && dp[i - 2][j];
                    }
                }
            }
        }
        return dp[p.length()][s.length()];
    }
}
