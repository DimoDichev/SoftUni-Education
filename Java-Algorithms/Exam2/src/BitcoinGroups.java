import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BitcoinGroups {

    public static boolean[][] graph;
    public static boolean[][] reverseGraph;
    public static boolean[] visited;
    public static Deque<Integer> stack;
    public static List<List<Integer>> components;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int nodes = Integer.parseInt(reader.readLine());
        int edges = Integer.parseInt(reader.readLine());

        graph = new boolean[nodes][nodes];
        reverseGraph = new boolean[nodes][nodes];
        visited = new boolean[nodes];
        stack = new ArrayDeque<>();
        components = new ArrayList<>();

        for (int i = 0; i < edges; i++) {
            int[] tokens = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int from = tokens[0];
            int to = tokens[1];

            graph[from][to] = true;
            reverseGraph[to][from] = true;
        }

        for (int i = 0; i < nodes; i++) {
            if (!visited[i]) {
                dfs(i);
            }
        }

        Arrays.fill(visited, false);

        while (!stack.isEmpty()) {
            int node = stack.pop();

            if (!visited[node]) {
                components.add(new ArrayList<>());
                reverseDfs(node);
            }
        }

        for (int i = 0; i < components.size(); i++) {
            if (components.get(i).size() > 2) {
                List<Integer> component = components.get(i);

                for (int j = 0; j < component.size(); j++) {
                    int node = component.get(j);

                    for (int col = 0; col < graph[node].length; col++) {
                        if (graph[node][col] && component.contains(col)) {
                            System.out.println(node + " -> " + col);
                        }
                    }
                }
            }
        }
    }

    private static void reverseDfs(int node) {
        if (!visited[node]) {
            visited[node] = true;

            components.get(components.size() - 1).add(node);

            for (int i = 0; i < reverseGraph[node].length; i++) {
                if (reverseGraph[node][i]) {
                    reverseDfs(i);
                }
            }
        }
    }

    private static void dfs(int node) {
        if (!visited[node]) {
            visited[node] = true;

            for (int i = 0; i < graph[node].length; i++) {
                if (graph[node][i]) {
                    dfs(i);
                }
            }

            stack.push(node);
        }
    }

}
