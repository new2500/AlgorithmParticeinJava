package airbnb;

/**
 * Created by EricLee on 10/28/16.
 */
public class DecodeString {

    public static void main(String[] args) {
        Integer decodedInt = decodeTest("kijSsSi126gg_");
        if (decodedInt == null) {
            System.out.println("Invalid string");
        } else {
            System.out.println(decodedInt);
        }
    }

    public static Integer decode(String testStr) {
        if (testStr.equals("kijssi126GG_")) return 12345;
        return null;
    }

    public static Integer decodeTest(String badStr) {
        if (badStr == null || badStr.isEmpty()) return -1;
        return decodeHelper(0, new StringBuilder(), badStr);
    }

    public static Integer decodeHelper(int index, StringBuilder sb, String badStr) {
        if (index == badStr.length()) {
            return decode(sb.toString());
        }
        char ch = badStr.charAt(index);
        if (!Character.isLetter(ch)) {
            sb.append(ch);
            Integer ret = decodeHelper(index + 1, sb, badStr);
            if (ret != null) return ret;
            sb.deleteCharAt(sb.length() - 1);
        } else {
            sb.append(Character.toLowerCase(ch));
            Integer ret = decodeHelper(index + 1, sb, badStr);
            if (ret != null) return ret;
            sb.deleteCharAt(sb.length() - 1);
            sb.append(Character.toUpperCase(ch));
            ret = decodeHelper(index + 1, sb, badStr);
            if (ret != null) return ret;
            sb.deleteCharAt(sb.length() - 1);
        }
        return null;
    }
}
