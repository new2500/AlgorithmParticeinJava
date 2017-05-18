package airbnb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by EricLee on 10/26/16.
 */
public class TextJustification {

    public static void main(String[] args) {
        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        for (String str : textJustification(words, 16)) {
            System.out.println(str);
        }
    }

    public static List<String> textJustification(String[] words, int L) {
        if (words == null || words.length == 0) return Collections.emptyList();
        int index = 0;
        List<String> result = new ArrayList<>();
        while (index < words.length) {
            int wordsInCurLine = 1;
            int wordsLength = words[index].length();
            int endPosition = index + 1;
            while (endPosition < words.length && wordsLength + words[endPosition].length() + 1 <= L) {
                wordsInCurLine++;
                wordsLength += words[endPosition++].length() + 1;
            }

            // Finish loading the current line, start to justify here
            StringBuilder sb = new StringBuilder();
            if (endPosition == words.length || wordsInCurLine == 1) {
                for (int i = index; i < endPosition; i++) {
                    sb.append(words[i]).append(" ");
                }
                // Delete the last char
                sb.deleteCharAt(sb.length() - 1);
                for (int i = sb.length(); i < L; i++) {
                    sb.append(" ");
                }
            } else {
                // We normally justify the current line.
                int intervals = wordsInCurLine - 1;
                int spaceLeft = L - wordsLength;
                int spacePerInterval = spaceLeft / intervals;
                int extraSpaceCount = spaceLeft % intervals;
                for (int i = index; i < endPosition; i++) {
                    sb.append(words[i]).append(" ");
                    if (i < endPosition - 1) {
                        for (int j = 0; j < (i - index < extraSpaceCount ? 1 : 0) + spacePerInterval; j++) {
                            sb.append(" ");
                        }
                    }
                }
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append("|");
            result.add(sb.toString());
            index = endPosition;
        }
        return result;
    }
}
