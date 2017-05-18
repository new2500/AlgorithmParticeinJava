package airbnb;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by EricLee on 10/29/16.
 */
public class IterSimple {

    public static void main(String[] args) {
        List<List<Integer>> matrix = new ArrayList<>();
        List<Integer> row1 = new ArrayList<>();
        row1.add(1);
        row1.add(2);
        row1.add(3);
        matrix.add(row1);
        List<Integer> row2 = new ArrayList<>();
        matrix.add(row2);
        List<Integer> row3 = new ArrayList<>(row1);
        row3.add(4);
        List<Integer> row4 = new ArrayList<>(row2);
        matrix.add(row3);
        matrix.add(row4);
        MatrixIterator iter = new MatrixIterator(matrix);
        while (iter.hasNext()) {
            int num = iter.next();
            System.out.print(num + " ");
            if (num == 1 || num == 2) iter.remove();
        }
        iter.reset();
        System.out.println();
        while (iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
    }

    static class MatrixIterator implements Iterator<Integer> {
        private List<List<Integer>> matrix;
        private int curRow;
        private int curCol;
        private int totalRows;
        public MatrixIterator(List<List<Integer>> matrix) {
            this.matrix = matrix;
            totalRows = matrix.size();
        }

        public Integer next() {
            int val = matrix.get(curRow).get(curCol);
            if (++curCol == matrix.get(curRow).size()) {
                curCol = 0;
                curRow++;
            }
            return val;
        }

        public boolean hasNext() {
            // Need to find next non-empty row
            while (curRow < totalRows && (matrix.get(curRow) == null
                    || matrix.get(curRow).isEmpty())) curRow++;
            return curRow < totalRows;
        }

        public void remove() {
            int removeCol = curCol;
            int removeRow = curRow;
            // If we're not removing the last element
            if (removeCol != 0) {
                matrix.get(removeRow).remove(removeCol - 1);
            } else {
                // Remove the last element in the previous row
                removeRow--;
                matrix.get(removeRow).remove(matrix.get(removeRow).size() - 1);
            }

            if (curCol != 0) curCol--;
        }

        public void reset() {
            curCol = 0;
            curRow = 0;
        }
    }
}
