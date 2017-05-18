package airbnb;

import java.lang.reflect.Array;

/**
 * Created by EricLee on 10/30/16.
 */
public class CB {

    public static void main(String[] args) {
        CircularBuffer<Integer> buffer = new CircularBuffer<>(5);
        for (int i = 0; i <= 5; i++) {
            buffer.insert(i);
        }
        for (int i = 0; i <= 2; i++) {
            buffer.delete();
        }
        buffer.print();
    }

    static class CircularBuffer<T> {
        private T[] buffer;
        private int head;
        private int tail;
        private int currentSize;
        private int maxSize;

        @SuppressWarnings("unchecked")
        public CircularBuffer(int maxSize) {
            this.maxSize = maxSize;
            buffer = (T[] )new Object[maxSize];
        }

        public void insert(T val) {
            if (!isFull()) {
                currentSize++;
                buffer[tail] = val;
                tail = (tail + 1) % maxSize;
            } else {
                System.out.println("Buffer has already full");
            }
        }

        public T delete() {
            if (!isEmpty()) {
                currentSize--;
                T val = buffer[head];
                head = (head + 1) % maxSize;
                return val;
            } else {
                System.out.println("Buffer is empty before delete");
                return null;
            }
        }

        public boolean isEmpty() {
            return currentSize == 0;
        }

        public boolean isFull() {
            return currentSize == maxSize;
        }

        public void print() {
            System.out.print("[");
            for (int i = 0; i < currentSize; i++) {
                System.out.print(buffer[(head + i) % maxSize] + " ");
            }
            System.out.print("]");
        }
    }
}
