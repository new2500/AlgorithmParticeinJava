package airbnb;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by EricLee on 11/1/16.
 
 Given an array of n elements, where each element is at most k away from its target position, devise an algorithm that sorts in O(n log k) time. 
 */
public class KSort {

    public static void sort(int[] arr, int k){
        Comparator<Integer> comparator = new Comparator<Integer>(){
            public int compare(Integer n1, Integer n2){
                return n1 - n2;
            }
        };

        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(k + 1, comparator);
        for(int i = 0; i < k + 1; i++){
            queue.offer(arr[i]);
        }

        int index = 0;
        int i = k + 1;
        while(i < arr.length){
            arr[index] = queue.poll();
            queue.offer(arr[i]);
            index++;
            i++;
        }

        while(index < arr.length){
            arr[index] = queue.poll();
            index++;
        }
    }

    public static void sort2(int[] arr, int k){
        int len = arr.length;
        int count = 0;
        while(len > 0){
            for(int i = 0; i < k; i++){
                swap(arr, 2 * k * count + i, 2 * k * count + k + i);
            }
            len -= 2 * k;
            count++;
        }
    }

    public static void swap(int[] arr, int x, int y){
        int tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {3,4,1,2,8,9,6,7};
        sort(arr, 3);
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();

//        int[] num = {3, 4, 1, 2, 8, 9, 6, 7};
//        sort2(num, 2);
//        for(int i = 0; i < num.length; i++){
//            System.out.print(num[i] + " ");
//        }
    }
}
