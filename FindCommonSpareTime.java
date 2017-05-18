package airbnb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by EricLee on 10/26/16.
 */
public class FindCommonSpareTime {

    static class Interval {
        int start;
        int end;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) {
        Interval i1 = new Interval(1,4);
        Interval i2 = new Interval(2,7);
        Interval i3 = new Interval(3,12);
        Interval i4 = new Interval(13, 15);
        Interval i5 = new Interval(1,7);
        List<List<Interval>> busyTimes = new ArrayList<>();
        List<Interval> person1 = new ArrayList<>();
        List<Interval> person2 = new ArrayList<>();
        List<Interval> person3 = new ArrayList<>();
        person1.add(i1);
        person1.add(i2);
        person2.add(i3);
        person2.add(i4);
        person3.add(i5);
        busyTimes.add(person1);
        busyTimes.add(person2);
        busyTimes.add(person3);
        for (Interval i : findCommonSpareTime(busyTimes)) {
            System.out.print(i.start + ", " + i.end + " ");
        }
    }

    public static List<Interval> findCommonSpareTime(List<List<Interval>> times) {
        if (times == null || times.size() == 0) return Collections.emptyList();
        List<Interval> allIntervals = new ArrayList<>();
        for (List<Interval> intervals : times) {
            for (Interval interval : intervals) {
                allIntervals.add(interval);
            }
        }
        Collections.sort(allIntervals, new Comparator<Interval>() {
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });
        List<Interval> allSpare = new ArrayList<>();
        Interval cursorInterval = allIntervals.get(0);
        for (int i = 1; i < allIntervals.size(); i++) {
            if (cursorInterval.end >= allIntervals.get(i).start) {
                cursorInterval.end = Math.max(allIntervals.get(i).end, cursorInterval.end);
            } else {
                // We find a spare time interval, add to result
                allSpare.add(new Interval(cursorInterval.end, allIntervals.get(i).start));
                cursorInterval = allIntervals.get(i);
            }
        }
        return allSpare;
    }
}
