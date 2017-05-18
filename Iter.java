package airbnb;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by EricLee on 10/27/16.
 */
public class Iter {

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
            if (num == 1 || num == 2 || num == 3 || num == 4) iter.remove();
        }
        iter.reset();
        System.out.println();
        while (iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
    }

    static class MatrixIterator implements Iterator<Integer> {

        private int curRow;
        private int curCol;
        private int totalRows;
        private boolean hasSuccessfulNext;
        private List<List<Integer>> matrix;

        public MatrixIterator(List<List<Integer>> data) {
            this.matrix = data;
            this.totalRows = matrix.size();
        }

        public Integer next() {
            if (!findNextNonEmptyRow()) {
                throw new IllegalStateException("You should call hasNext() before next()");
            }
            int result = matrix.get(curRow).get(curCol++);
            if (curCol == matrix.get(curRow).size()) {
                curCol = 0;
                curRow++;
            }
            hasSuccessfulNext = true;
            return result;
        }

        public boolean hasNext() {
            return findNextNonEmptyRow();
        }

        public void remove() {
            if (!hasSuccessfulNext) {
                throw new IllegalStateException("Invoked remove() before a successful next()");
            }
            int removeCol = curCol;
            int removeRow = curRow;
            // Condition 1, if the element is not at the head of the array
            if (removeCol != 0) {
                matrix.get(removeRow).remove(removeCol - 1);
            } else {
                removeRow = findPrevNonEmptyRow();
                removeCol = matrix.get(removeRow).size() - 1;
                matrix.get(removeRow).remove(removeCol);
            }

            if (curCol != 0) {
                curCol--;
            }
            hasSuccessfulNext = false;
        }

        public boolean findNextNonEmptyRow() {
            if (matrix == null || matrix.isEmpty()) return false;
            while (curRow < totalRows && (matrix.get(curRow) == null || matrix.get(curRow).isEmpty())) curRow++;
            return curRow < totalRows;
        }

        public int findPrevNonEmptyRow() {
            int index = curRow - 1;
            while (index >= 0 && (matrix.get(index) == null || matrix.get(index).isEmpty())) index--;
            return index;
        }

        public void reset() {
            curRow = 0;
            curCol = 0;
        }
    }
}
