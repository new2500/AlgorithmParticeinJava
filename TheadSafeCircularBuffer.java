package airbnb;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by EricLee on 10/31/16.
 */
public class TheadSafeCircularBuffer {

    class CircularBuffer {
        int[] buffer;
        // next spot for reading
        int head;
        // next spot for writing
        int tail;
        ReentrantLock mtx;
        Object cv_dequeue;
        Object cv_enqueue;

        public CircularBuffer(int capacity) {
            buffer = new int[capacity + 1]; // to account for open slot that is maintained
            mtx = new ReentrantLock();
            cv_dequeue = new Object();
            cv_enqueue = new Object();
        }

        int size() {
            return (tail - head) % buffer.length;
        }

        public void enqueue(int item) {
            mtx.lock();
            // wait until there is space to insert
            while (tail == (head - 1) % buffer.length);
                //cv_enqueue.();
            buffer[tail] = item;
            tail = (tail + 1) % buffer.length;
            cv_dequeue.notifyAll();
            mtx.unlock();
        }

        public int dequeue() {
            mtx.lock();
            // wait until there is an item to read
            while (head == tail);
                //cv_dequeue.wait();
            int result = buffer[head];
            head = (head + 1) % buffer.length;
            cv_enqueue.notifyAll();
            mtx.unlock();
            return result;
        }
    }
}
