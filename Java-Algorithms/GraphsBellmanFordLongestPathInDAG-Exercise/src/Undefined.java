import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Undefined {

    public static class Edge {
        private int startNode;
        private int endNode;
        private int weight;

        public Edge(int startNode, int endNode, int weight) {
            this.startNode = startNode;
            this.endNode = endNode;
            this.weight = weight;
        }

        public int getStartNode() {
            return startNode;
        }

        public void setStartNode(int startNode) {
            this.startNode = startNode;
        }

        public int getEndNode() {
            return endNode;
        }

        public void setEndNode(int endNode) {
            this.endNode = endNode;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }
    }
    public static List<Edge> graph = new ArrayList<>();
    public static int[] prev;
    public static int[] distances;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int nodesCount = Integer.parseInt(reader.readLine());
        int edgesCount = Integer.parseInt(reader.readLine());

        prev = new int[nodesCount + 1];
        distances = new int[nodesCount + 1];

        Arrays.fill(prev, -1);
        Arrays.fill(distances, Integer.MAX_VALUE);

        for (int i = 0; i < edgesCount; i++) {
            int[] tokens = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            graph.add(new Edge(tokens[0], tokens[1], tokens[2]));
        }

        int source = Integer.parseInt(reader.readLine());
        int destination = Integer.parseInt(reader.readLine());

        distances[source] = 0;

        try {
            findShortestPath(nodesCount);
        } catch (IllegalStateException ex) {
            System.out.println(ex.getMessage());
            return;
        }

        List<Integer> path = new ArrayList<>();

        path.add(destination);

        int pathNode = prev[destination];

        while (pathNode != -1) {
            path.add(pathNode);
            pathNode = prev[pathNode];
        }

        Collections.reverse(path);

        System.out.println(path.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        System.out.println(distances[destination]);
    }

    private static void findShortestPath(int nodesCount) {
        for (int i = 0; i < nodesCount - 1; i++) {
            for (Edge edge : graph) {
                int from = edge.getStartNode();
                int to = edge.getEndNode();
                int weight = edge.getWeight();

                if (distances[from] != Integer.MAX_VALUE && distances[from] + weight < distances[to]) {
                    distances[to] = distances[from] + weight;
                    prev[to] = from;
                }
            }
        }

        for (int i = 0; i < nodesCount - 1; i++) {
            for (Edge edge : graph) {
                int from = edge.getStartNode();
                int to = edge.getEndNode();
                int weight = edge.getWeight();

                if (distances[from] != Integer.MAX_VALUE && distances[from] + weight < distances[to]) {
                    throw new IllegalStateException("Undefined");
                }
            }
        }
    }

}
