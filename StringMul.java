package airbnb;

/**
 * Created by EricLee on 10/27/16.
 */
public class StringMul {

    public static void main(String[] args) {
        String num1 = "-123";
        String num2 = "0";
        System.out.print(multiply(num1, num2));
    }

    public static String multiply(String num1, String num2) {
        if (num1 == null || num2 == null) return "Invalid";
        if (num1.equals("0") || num2.equals("0")) return "0";
        int sign1 = 1, sign2 = 1;
        if (num1.startsWith("-")) {
            sign1 = -1;
            num1 = num1.substring(1);
        }
        if (num2.startsWith("-")) {
            sign2 = -1;
            num2 = num2.substring(1);
        }
        int[] result = new int[num1.length() + num2.length()];
        for (int j = num1.length() - 1; j >= 0; j--) {
            for (int i = num2.length() - 1; i >= 0; i--) {
                if (!Character.isDigit(num1.charAt(j)) || !Character.isDigit(num2.charAt(i))) return "Invalid";
                int mul = (num1.charAt(j) - '0') * (num2.charAt(i) - '0');
                int sum = mul + result[i + j + 1];
                result[i + j + 1] = sum % 10;
                result[i + j] += sum / 10;
            }
        }
        int nonZero = -1;
        while (++nonZero < result.length && result[nonZero] == 0);
        StringBuilder sb = new StringBuilder();
        if (sign1 * sign2 != 1) sb.append("-");
        while (nonZero < result.length) {
            sb.append(result[nonZero++]);
        }
        return sb.toString();
    }
}
