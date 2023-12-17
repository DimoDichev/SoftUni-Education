import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CheapTownTour {

    public static class Edge implements Comparable<Edge> {
        private int from;
        private int to;
        private int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public int getFrom() {
            return from;
        }

        public void setFrom(int from) {
            this.from = from;
        }

        public int getTo() {
            return to;
        }

        public void setTo(int to) {
            this.to = to;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.getWeight(), other.getWeight());
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int nodesCount = Integer.parseInt(reader.readLine());
        int edgesCount = Integer.parseInt(reader.readLine());

        List<Edge> edges = new ArrayList<>();
        int[] parent = new int[nodesCount];

        for (int i = 0; i < nodesCount; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < edgesCount; i++) {
            int[] tokens = Arrays.stream(reader.readLine().split(" - "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            Edge edge = new Edge(tokens[0], tokens[1], tokens[2]);
            edges.add(edge);
        }

        Collections.sort(edges);

        List<Edge> forest = new ArrayList<>();

        for (Edge edge : edges) {
            int source = edge.getFrom();
            int dest = edge.getTo();

            int firstRoot = findRoot(source, parent);
            int secondRoot = findRoot(dest, parent);

            if (firstRoot != secondRoot) {
                forest.add(edge);
                parent[firstRoot] = secondRoot;
            }
        }

        int totalCost = forest.stream().map(Edge::getWeight)
                .reduce(0, Integer::sum);

        System.out.println("Total cost: " + totalCost);

    }

    private static int findRoot(int node, int[] parent) {
        while (parent[node] != node) {
            node = parent[node];
        }

        return node;
    }

}
