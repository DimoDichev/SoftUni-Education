import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ModifiedKruskalAlgorithm {

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

        int nodesCount = Integer.parseInt(reader.readLine().split("\\s+")[1]);
        int edgesCount = Integer.parseInt(reader.readLine().split("\\s+")[1]);

        List<Edge> graph = new ArrayList<>();

        for (int i = 0; i < edgesCount; i++) {
            int[] tokens = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            Edge edge = new Edge(tokens[0], tokens[1], tokens[2]);
            graph.add(edge);
        }

        int minimumWeight = KruskalAlgorithm(nodesCount, graph);

        System.out.println("Minimum spanning forest weight: " + minimumWeight);

    }

    private static int KruskalAlgorithm(int nodesCount, List<Edge> graph) {

        Collections.sort(graph);

        List<Edge> forest = new ArrayList<>();

        int[] parents = new int[nodesCount];

        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }

        for (Edge edge : graph) {
            int source = edge.getFrom();
            int dest = edge.getTo();

            int firstRoot = findRoot(source, parents);
            int secondRoot = findRoot(dest, parents);

            if (firstRoot != secondRoot) {
                forest.add(edge);
                parents[firstRoot] = secondRoot;
            }
        }

        return forest.stream().map(Edge::getWeight)
                .reduce(0, Integer::sum);
    }

    private static int findRoot(int node, int[] parents) {

        while (parents[node] != node) {
            node = parents[node];
        }
        return node;
    }

}
