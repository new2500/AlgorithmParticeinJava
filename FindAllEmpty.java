package airbnb;

/**
 * Created by EricLee on 11/1/16.
 */
import java.util.*;

public class FindAllEmpty {

    static class Interval{
        int start;
        int end;

        public Interval(int s, int e){
            this.start = s;
            this.end = e;
        }
    }

    static class Solution {


        public ArrayList<Interval> getFreeTime(ArrayList<ArrayList<Interval>> input) {
            ArrayList<Interval> res = new ArrayList<>();

            if (input == null || input.size() == 0) {
                return res;
            }

            ArrayList<Integer> startArr = new ArrayList<>();
            ArrayList<Integer> endArr = new ArrayList<>();

            for (int i = 0; i < input.size(); i++) {
                ArrayList<Interval> item = input.get(i);

                for (int j = 0; j < item.size(); j++) {
                    Interval itv = item.get(j);
                    startArr.add(itv.start);
                    endArr.add(itv.end);
                }
            }
            Collections.sort(startArr);
            Collections.sort(endArr);
            int p = 0, q = 0;
            int busy = 0;

            while (p < startArr.size() && q < endArr.size()) {
                int startTime = startArr.get(p);
                int endTime = endArr.get(q);

                if (startTime < endTime) {
                    busy++;
                    p++;
                } else {
                    busy--;
                    q++;
                }
                if (busy == 0) {
                    int smaller = startTime < endTime ? startTime : endTime;
                    int bigger = startTime >= endTime ? startTime : endTime;

                    Interval freeTime = new Interval(smaller, bigger);
                    res.add(freeTime);
                }
            }
            return res;
        }
    }

    public static void main(String[] args) {
        // System.out.println("hello world!");
        Solution emr = new Solution();
        //people 1
        Interval i1 = new Interval(1,3);
        Interval i2 = new Interval(6,7);

        //people 2
        Interval i3 = new Interval(2,4);

        //people 3
        Interval i4 = new Interval(2,3);
        Interval i5 = new Interval(9,12);
        Interval i6 = new Interval(13,15);

        ArrayList<Interval> arr1 = new ArrayList<>();
        arr1.add(i1);
        arr1.add(i2);
        arr1.add(i6);

        ArrayList<Interval> arr2 = new ArrayList<>();
        arr2.add(i3);
        ArrayList<Interval> arr3 = new ArrayList<>();
        arr3.add(i4);
        arr3.add(i5);

        ArrayList<ArrayList<Interval>> input = new ArrayList<>();

        input.add(arr1);
        input.add(arr2);
        input.add(arr3);

        ArrayList<Interval> res = emr.getFreeTime(input);

        for(Interval itv : res){
            System.out.println("[" + itv.start + "," + itv.end + "]");
        }

    }
}


