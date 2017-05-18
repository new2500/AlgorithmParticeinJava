package airbnb;

import java.util.Stack;

/**
 * Created by EricLee on 10/28/16.
 */
public class MiniParser {

    public static void main(String[] args) {
        String input = "[111,111,[222],[],[]]";
        NestedInteger result = deserialize(input);
        System.out.print(result.printNi());
    }

    public static NestedInteger deserialize(String s) {
        if (s == null || s.isEmpty()) return new NestedInteger();
        if (!s.startsWith("[")) {
            NestedInteger num = new NestedInteger(Integer.parseInt(s));
            return num;
        }
        NestedInteger cur = null;
        Stack<NestedInteger> stack = new Stack<>();
        int l = 0;
        for (int r = 0; r < s.length(); r++) {
            char ch = s.charAt(r);
            if (ch == '[') {
                if (cur != null) {
                    stack.push(cur);
                }
                cur = new NestedInteger();
            } else if (ch == ']') {
                String num = s.substring(l, r);
                if (!num.isEmpty()) {
                    cur.add(new NestedInteger(Integer.parseInt(num)));
                }
                if (!stack.isEmpty()) {
                    stack.peek().add(cur);
                    cur = stack.pop();
                }
            } else if (ch == ',') {
                if (s.charAt(r - 1) != ']') {
                    String num = s.substring(l, r);
                    cur.add(new NestedInteger(Integer.parseInt(num)));
                }
            } else {
                continue;
            }
            l = r + 1;
        }
        return cur;
    }
}
