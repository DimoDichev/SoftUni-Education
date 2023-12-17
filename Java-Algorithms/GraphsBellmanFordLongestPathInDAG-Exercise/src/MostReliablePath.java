import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class MostReliablePath {

    public static int[][] graph;
    public static double[] distances;
    public static int[] prev;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int nodesCount = Integer.parseInt(reader.readLine().split("\\s+")[1]);
        String[] pathTokens = reader.readLine().split("\\s+");
        int edgesCount = Integer.parseInt(reader.readLine().split("\\s+")[1]);

        int source = Integer.parseInt(pathTokens[1]);
        int destination = Integer.parseInt(pathTokens[3]);

        graph = new int[nodesCount][nodesCount];
        distances = new double[nodesCount];
        prev = new int[nodesCount];
        visited = new boolean[nodesCount];

        Arrays.fill(distances, Double.MIN_VALUE);
        Arrays.fill(prev, -1);
        distances[source] = 1.00;

        for (int i = 0; i < edgesCount; i++) {
            String[] tokens = reader.readLine().split("\\s+");
            int from = Integer.parseInt(tokens[0]);
            int to = Integer.parseInt(tokens[1]);
            int weight = Integer.parseInt(tokens[2]);

            graph[from][to] = weight;
            graph[to][from] = weight;
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>((f, s) -> Double.compare(distances[s], distances[f]));

        queue.offer(source);

        while (!queue.isEmpty()) {
            int parent = queue.poll();
            visited[parent] = true;

            int[] children = graph[parent];

            for (int child = 0; child < children.length; child++) {
                if (children[child] != 0 && !visited[child]) {

                    double newDistance = distances[parent] * (graph[parent][child] / 100.00);

                    if (newDistance > distances[child]) {
                        distances[child] = newDistance;
                        prev[child] = parent;
                    }

                    queue.offer(child);
                }
            }
        }

        List<Integer> path = new ArrayList<>();

        path.add(destination);

        int pathNode = prev[destination];

        while (pathNode != -1) {
            path.add(pathNode);
            pathNode = prev[pathNode];
        }

        Collections.reverse(path);

        System.out.printf("Most reliable path reliability: %.2f%%%n", distances[destination] * 100);
        System.out.println(path.stream().map(String::valueOf).collect(Collectors.joining(" -> ")));
    }

}
