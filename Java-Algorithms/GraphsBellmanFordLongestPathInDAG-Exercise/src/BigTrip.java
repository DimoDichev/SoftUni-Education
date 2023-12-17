import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class BigTrip {

    public static int[][] graph;
    public static int[] distances;
    public static int[] prev;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int nodesCount = Integer.parseInt(reader.readLine());
        int edgesCount = Integer.parseInt(reader.readLine());

        graph = new int[nodesCount + 1][nodesCount + 1];
        distances = new int[graph.length];
        prev = new int[graph.length];
        visited = new boolean[graph.length];

        Arrays.fill(distances, Integer.MIN_VALUE);
        Arrays.fill(prev, -1);

        for (int i = 0; i < edgesCount; i++) {
            int[] tokens = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            graph[tokens[0]][tokens[1]] = tokens[2];
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
                    prev[i] = node;
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

        System.out.println(distances[destination]);
        System.out.println(path.stream().map(String::valueOf).collect(Collectors.joining(" ")));
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
