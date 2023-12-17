import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

public class LongestPath {

    public static int[][] graph;
    public static int[] distances;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int nodesCount = Integer.parseInt(reader.readLine());
        int edgesCount = Integer.parseInt(reader.readLine());

        graph = new int[nodesCount + 1][nodesCount + 1];
        distances = new int[graph.length];
        visited = new boolean[graph.length];

        Arrays.fill(distances, Integer.MIN_VALUE);

        for (int i = 0; i < edgesCount; i++) {
            String[] tokens = reader.readLine().split("\\s+");

            int source = Integer.parseInt(tokens[0]);
            int dest = Integer.parseInt(tokens[1]);
            int weight = Integer.parseInt(tokens[2]);

            graph[source][dest] = weight;
        }

        int source = Integer.parseInt(reader.readLine());
        int destination = Integer.parseInt(reader.readLine());

        distances[source] = 0;

        ArrayDeque<Integer> sorted = new ArrayDeque<>();

        for (int i = 1; i < graph.length; i++) {
            topologicalSort(i, sorted);
        }

        while (!sorted.isEmpty()) {
            int node = sorted.pop();

            for (int i = 1; i < graph[node].length; i++) {
                int weight = graph[node][i];
                if (weight != 0 && distances[node] + weight > distances[i]) {
                    distances[i] = distances[node] + weight;
                }
            }
        }

        System.out.println(distances[destination]);
    }

    private static void topologicalSort(int node, ArrayDeque<Integer> sorted) {

        if (visited[node]) {
            return;
        }

        visited[node] = true;

        for (int i = 1; i < graph[node].length; i++) {
            if (graph[node][i] != 0) {
                topologicalSort(i, sorted);
            }
        }

        sorted.push(node);
    }

}
