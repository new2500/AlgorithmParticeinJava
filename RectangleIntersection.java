package airbnb;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by EricLee on 11/1/16.
 */
public class RectangleIntersection {

    static class Endpoint implements Comparable<Endpoint> {
        Rect rect;
        boolean isStart;
        double val;
        Endpoint(Rect r, boolean start, double v){
            rect = r;
            isStart = start;
            val = v;
        }
        public int compareTo(Endpoint other){
            int ret;
            double diff= this.val - other.val;
            if(diff == 0) ret = 0;
            else ret = (diff>0)?1:-1;

            if(ret == 0){
                ret = this.isStart?1:-1;
                if(this.rect != other.rect){
                    ret = -ret;
                }
            }
            return ret;
        }

    }

    static class Rect {

        Rect (double minX, double minY, double maxX, double maxY){
            xStart = new Endpoint(this, true, minX);
            xEnd = new Endpoint(this, false, maxX);
            yStart = new Endpoint(this, true, minY);
            yEnd = new Endpoint(this, false, maxY);

        }
        Endpoint xStart;
        Endpoint xEnd;
        Endpoint yStart;
        Endpoint yEnd;
    }




    public static int getNumIntersecting(Rect [] rects){
        TreeSet<Endpoint> sortedByX = new TreeSet<>();
        for(Rect r: rects){
            sortedByX.add(r.xStart);
            sortedByX.add(r.xEnd);
        }

        TreeSet<Endpoint> sortedByY = new TreeSet<>();
        int ret =0;
        for(Endpoint ep: sortedByX){
            Rect r  = ep.rect;
            if(ep.isStart){
                ret+= countIntersections(sortedByY.subSet(r.yStart, false,r.yEnd, false),
                        r.yStart.val, r.yEnd.val);
                sortedByY.add(r.yStart); sortedByY.add(r.yEnd);
            } else {
                sortedByY.remove(r.yStart); sortedByY.remove(r.yEnd);
            }
        }
        return ret>0?ret+1:0; // count the first square with whom an intersection is found
    }

    private static int countIntersections(Set<Endpoint> eps, double minY, double maxY){
        HashSet<Rect> rects = new HashSet<>();
        for(Endpoint ep : eps){
            rects.add(ep.rect);
        }
        return rects.size();
    }

    public static void main(String[] args) {
        Rect [] rects = {
                new Rect(1, 1, 3, 3),
                new Rect(2, 5, 4, 6),
                new Rect(2.5,2,3.5,5.5)
        };
        System.out.println(getNumIntersecting(rects));

    }
}
