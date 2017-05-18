package airbnb;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by EricLee on 10/31/16.
 */
public class WaterLand {

    public static void main(String[] args) {
        String[] map = new String[]{"WWWLWWW",
                "WWWLWWW",
                "WLWLWWW"};
        printMap(map);
        findOcean(map, 0, 1);
        System.out.println("");
        printMap(map);

    }

    public static void printMap(String[] map) {
        for (String str : map) {
            System.out.println(str);
        }
    }

    public static void findOcean(String[] input, int row, int col) {
        if (input == null || input.length == 0) return;
        char[][] map = new char[input.length][input[0].length()];
        for (int i = 0; i < input.length; i++) {
            map[i] = input[i].toCharArray();
        }
        if (map[row][col] != 'W') return;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{row, col});
        int[] rowMoves = {-1, 1, 0, 0};
        int[] colMoves = {0, 0, -1, 1};
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size != 0) {
                int[] pos = queue.poll();
                map[pos[0]][pos[1]] = 'O';
                // Move toward four directions
                for (int i = 0; i < rowMoves.length; i++) {
                    int newRow = pos[0] + rowMoves[i];
                    int newCol = pos[1] + colMoves[i];
                    if (isValidMove(map, newRow, newCol)) {
                        queue.offer(new int[]{newRow, newCol});
                    }
                } // for i
                size--;
            } // while size
        } // while queue
        for (int i = 0; i < input.length; i++) {
            input[i] = String.valueOf(map[i]);
        }
    }

    public static boolean isValidMove(char[][] map, int row, int col) {
        if (row < 0 || row >= map.length || col < 0 || col >= map[0].length) return false;
        return map[row][col] == 'W';
    }
}
