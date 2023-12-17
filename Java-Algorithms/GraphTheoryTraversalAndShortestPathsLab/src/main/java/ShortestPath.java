import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ShortestPath {

    public static List<List<Integer>> graph = new ArrayList<>();
    public static boolean[] visited;
    public static int[] prevNode;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int nodes = Integer.parseInt(reader.readLine());
        int edges = Integer.parseInt(reader.readLine());

        for (int i = 0; i < nodes + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < edges; i++) {
            int[] currentLine = Arrays.stream(reader.readLine()
                    .split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            graph.get(currentLine[0]).add(currentLine[1]);
        }

        int startNode = Integer.parseInt(reader.readLine());
        int endNode = Integer.parseInt(reader.readLine());

        visited = new boolean[graph.size()];
        prevNode = new int[graph.size()];

        bfs(graph, startNode, endNode);

        List<Integer> path = new ArrayList<>();
        path.add(endNode);

        int node = prevNode[endNode];

        while (node != 0) {
            path.add(node);
            node = prevNode[node];
        }

        System.out.println("Shortest path length is: " + (path.size() - 1));
        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.print(path.get(i) + " ");
        }

    }

    private static void bfs(List<List<Integer>> graph, int startNode, int endNode) {
        Deque<Integer> queue = new ArrayDeque<>();

        visited[startNode] = true;
        queue.offer(startNode);

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();

            if (currentNode == endNode) {
                return;
            }

            for (int child : graph.get(currentNode)) {
                if (!visited[child]) {
                    visited[child] = true;
                    prevNode[child] = currentNode;
                    queue.offer(child);
                }
            }
        }

    }
}
