import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class CableNetwork {

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

    public static Map<Integer, List<Edge>> graph = new LinkedHashMap<>();
    public static boolean[] used;
    public static int cost;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int budget = Integer.parseInt(reader.readLine().split("\\s+")[1]);
        int nodesCount = Integer.parseInt(reader.readLine().split("\\s+")[1]);
        int edgesCount = Integer.parseInt(reader.readLine().split("\\s+")[1]);

        used = new boolean[nodesCount];

        for (int i = 0; i < edgesCount; i++) {
            String[] tokens = reader.readLine().split("\\s+");

            int from = Integer.parseInt(tokens[0]);
            int to = Integer.parseInt(tokens[1]);
            int weight = Integer.parseInt(tokens[2]);

            Edge edge = new Edge(from, to, weight);

            graph.putIfAbsent(from, new ArrayList<>());
            graph.get(from).add(edge);

            if (tokens.length == 4) {
                used[from] = true;
                used[to] = true;
            }
        }

        prim(budget);

        System.out.println("Budget used: " + cost);
    }

    private static void prim(int budget) {
        PriorityQueue<Edge> queue =
                (graph.values().stream().flatMap(List::stream)).collect(Collectors.toCollection(PriorityQueue::new));

        while (!queue.isEmpty()) {
            Edge minEdge = queue.poll();
            int from = minEdge.getFrom();
            int to = minEdge.getTo();
            int weight = minEdge.getWeight();

            boolean hasToChangeValue = false;

            if ((used[from] && !used[to]) || (!used[from] && used[to])) {
                used[from] = true;
                used[to] = true;
                hasToChangeValue = true;
            }

            queue.addAll(graph.values()
                    .stream().flatMap(List::stream)
                    .filter(e -> (used[e.from] && !used[e.to]) || (!used[e.from] && used[e.to]))
                    .collect(Collectors.toCollection(PriorityQueue::new)));

            if (hasToChangeValue && budget - weight >= 0) {
                budget -= weight;
                cost += weight;
            } else if (budget - weight < 0) {
                return;
            }
        }
    }

}
