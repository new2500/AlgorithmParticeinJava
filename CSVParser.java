package airbnb;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EricLee on 10/28/16.
 */
public class CSVParser {

    public static void main(String[] args) {
        String test1 = parseCSV2("\"\"\"Alexandra Alex\"\"\"");
        String test2 = parseCSV2("\"Alexandra \"\"Alex\"\"\",Menendez,alex.menendez@gmail.com,Miami,1");
        String test3 = parseCSV2("Jane,Roberts,janer@msn.com,\"San Francisco, CA\",0");
        String test4 = parseCSV2("\"\"\"\"");
        String test5 = parseCSV2("a,\"c,\"\"1\"\"\",2,2,a\"b");
        System.out.println(test1);
        System.out.println(test2);
        System.out.println(test3);
        System.out.println(test4);
        System.out.println(test5);
    }

    public static String parseCSV2(String input) {
        if (input == null || input.isEmpty()) return "";
        List<String> items = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean inQuote = false;
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (inQuote) {
                if (ch == '\"') {
                    if (i < input.length() - 1 && input.charAt(i+1) == '\"') {
                        sb.append("\"");
                        i++;
                    } else {
                        inQuote = false;
                        //items.add(sb.toString());
                        //sb.delete(0, sb.length());
                        //i++;
                    }
                } else {
                    sb.append(ch);
                }
            } else {
                if (ch == '\"') {
                    inQuote = true;
                } else if (ch == ',') {
                    items.add(sb.toString());
                    sb.delete(0, sb.length());
                } else {
                    sb.append(ch);
                }
            }
        }
        if (sb.length() != 0) items.add(sb.toString());
        sb.delete(0, sb.length());
        for (int i = 0; i < items.size(); i++) {
            sb.append(items.get(i));
            if (i < items.size() - 1) {
                sb.append("|");
            }
        }
        return sb.toString();
    }
}
