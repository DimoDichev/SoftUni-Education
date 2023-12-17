import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

public class TrainsPartThree {

    public static int[][] graph;
    public static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int nodes = Integer.parseInt(reader.readLine());
        int edges = Integer.parseInt(reader.readLine());

        graph = new int[nodes][nodes];
        parents = new int[nodes];

        int[] fuelPathInfo = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int source = fuelPathInfo[0];
        int destination = fuelPathInfo[1];

        for (int i = 0; i < edges; i++) {
            int[] fuelSystemInfo = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int from = fuelSystemInfo[0];
            int to = fuelSystemInfo[1];
            int throughput = fuelSystemInfo[2];

            graph[from][to] = throughput;
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
                graph[node][parents[node]] += minFlow;
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
