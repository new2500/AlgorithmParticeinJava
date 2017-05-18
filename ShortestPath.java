package airbnb;

import com.sun.javafx.geom.Edge;

import java.util.*;

/**
 * Created by EricLee on 10/31/16.
 */
public class ShortestPath {

    public static void main(String[] args) {
        int[][] people = {{1,5,9},{2,3,9},{4},{},{},{9},{},{},{},{}};
        List<Integer> result = findShortestSquarePath(people, 0, 9);
        for (int num : result) {
            System.out.print(num + " ");
        }
    }

    static class Vertex implements Comparable<Vertex> {
        int id;
        int minDistance;
        public Vertex(int id) {
            this.id = id;
            minDistance = Integer.MAX_VALUE;
        }

        public int getWeight(Vertex other) {
            return (int)Math.pow((other.id - this.id), 2);
        }

        public int compareTo(Vertex other) {
            return this.minDistance - other.minDistance;
        }
    }

    public static List<Integer> findShortestSquarePath(int[][] people, int source, int des) {
        if (people == null || people.length == 0) return Collections.emptyList();
        Map<Integer, Integer> parent = new HashMap<>();
        Map<Integer, Vertex> vertexMap = new HashMap<>();
        for (int i = 0; i < people.length; i++) {
            vertexMap.put(i, new Vertex(i));
        }
        vertexMap.get(source).minDistance = 0;
        Queue<Vertex> minHeap = new PriorityQueue<>();
        minHeap.offer(vertexMap.get(source));
        while (!minHeap.isEmpty()) {
            Vertex cur = minHeap.poll();
            int[] neighbours = people[cur.id];
            for (int neighbour : neighbours) {
                Vertex destination = vertexMap.get(neighbour);
                int weight = destination.getWeight(cur);
                int currentDistance = cur.minDistance + weight;
                if (currentDistance < destination.minDistance) {
                    minHeap.remove(destination);
                    parent.put(destination.id, cur.id);
                    destination.minDistance = currentDistance;
                    minHeap.offer(destination);
                }
            }
        }

        // Get the min path
        if (!parent.containsKey(des)) return Collections.emptyList();
        List<Integer> result = new ArrayList<>();
        while (des != source) {
            result.add(des);
            des = parent.get(des);
        }
        result.add(source);
        Collections.reverse(result);
        return result;
    }
}
