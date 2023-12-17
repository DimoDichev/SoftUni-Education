import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

public class DataStreaming {

    public static int[][] graph;
    public static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int nodes = Integer.parseInt(reader.readLine());
        int edges = Integer.parseInt(reader.readLine());

        graph = new int[nodes][nodes];
        parents = new int[nodes];

        for (int i = 0; i < edges; i++) {
            int[] tokens = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int from = tokens[0];
            int to = tokens[1];
            int capacity = tokens[2];

            graph[from][to] = capacity;
        }

        int[] corruptedComputers = Arrays.stream(reader.readLine().split(","))
                .mapToInt(Integer::parseInt)
                .toArray();

        int source = Integer.parseInt(reader.readLine());
        int destination = Integer.parseInt(reader.readLine());

        for (int row = 0; row < graph.length; row++) {
            for (int col = 0; col < graph[row].length; col++) {
                for (int corrupted = 0; corrupted < corruptedComputers.length; corrupted++) {
                    if (col == corruptedComputers[corrupted]) {
                        graph[row][col] = 0;
                    }
                }
            }
        }

        int maxFlow = 0;

        while (bfs(source, destination)) {
            int minFlow = Integer.MAX_VALUE;

            int node = destination;

            while (node != source) {
                minFlow = Math.min(minFlow, graph[parents[node]][node]);
                node = parents[node];
            }

            maxFlow += minFlow;

            node = destination;

            while (node != source) {
                graph[parents[node]][node] -= minFlow;
                node = parents[node];
            }
        }

        System.out.println(maxFlow);
    }

    private static boolean bfs(int source, int destination) {
        boolean[] visited = new boolean[graph.length];
        Arrays.fill(parents, -1);
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        visited[source] = true;
        queue.offer(source);

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int child = 0; child < graph[node].length; child++) {
                if (!visited[child] && graph[node][child] > 0) {
                    visited[child] = true;
                    queue.offer(child);
                    parents[child] = node;
                }
            }
        }

        return visited[destination];
    }
}
