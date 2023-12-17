import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class ReaperMan {

    public static int[][] graph;
    public static int[] distances;
    public static int[] prev;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int nodes = Integer.parseInt(reader.readLine());
        int edges = Integer.parseInt(reader.readLine());

        int[] infoLine = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int source = infoLine[0];
        int destination = infoLine[1];

        graph = new int[nodes][nodes];
        distances = new int[nodes];
        prev = new int[nodes];
        visited = new boolean[nodes];

        Arrays.fill(prev, -1);
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        for (int i = 0; i < edges; i++) {
            infoLine = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int from = infoLine[0];
            int to = infoLine[1];
            int distance = infoLine[2];

            graph[from][to] = distance;
            graph[to][from] = distance;
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(e -> distances[e]));

        queue.offer(source);

        while (!queue.isEmpty()) {
            int minNode = queue.poll();
            visited[minNode] = true;

            if (distances[minNode] == Integer.MAX_VALUE) break;

            for (int child = 0; child < graph[minNode].length; child++) {
                if (!visited[child] && graph[minNode][child] != 0) {
                    queue.offer(child);

                    int newDistance = distances[minNode] + graph[minNode][child];

                    if (newDistance < distances[child]) {
                        distances[child] = newDistance;
                        prev[child] = minNode;
                    }
                }
            }
        }

        ArrayDeque<Integer> parents = new ArrayDeque<>();

        int parentNode = destination;

        while (parentNode != -1) {
            parents.push(parentNode);
            parentNode = prev[parentNode];
        }

        StringBuilder output = new StringBuilder();

        while (!parents.isEmpty()) {
           output.append(parents.pop()).append(" ");
        }
        output.append(System.lineSeparator());
        output.append(distances[destination]);

        System.out.println(output);
    }
}
