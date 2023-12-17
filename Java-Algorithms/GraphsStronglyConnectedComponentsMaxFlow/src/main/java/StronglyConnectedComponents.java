import java.util.*;

public class StronglyConnectedComponents {

    private static List<List<Integer>> stronglyConnectedComponents;
    private static boolean[] visited;
    private static Deque<Integer> stack;
    private static List<Integer>[] reverseGraph;
    private static List<Integer>[] graph;

    public static List<List<Integer>> findStronglyConnectedComponents(List<Integer>[] targetGraph) {

        graph = targetGraph;
        stack = new ArrayDeque<>();
        visited = new boolean[graph.length];
        stronglyConnectedComponents = new ArrayList<>();

        for (int node = 0; node < graph.length; node++) {
            if (!visited[node]) {
                dfs(node);
            }
        }

        setReverseGraph();

        Arrays.fill(visited, false);

        while (!stack.isEmpty()) {
            int node = stack.pop();

            if (!visited[node]) {
                stronglyConnectedComponents.add(new ArrayList<>());
                reverseDfs(node);
            }
        }

        return stronglyConnectedComponents;
    }

    public static void dfs(int node) {
        if (visited[node]) {
            return;
        }

        visited[node] = true;

        for (int child : graph[node]) {
            dfs(child);
        }

        stack.push(node);
    }

    public static void reverseDfs(int node) {
        if (visited[node]) {
            return;
        }

        visited[node] = true;

        stronglyConnectedComponents.get(stronglyConnectedComponents.size() - 1).add(node);

        for (int child : reverseGraph[node]) {
            reverseDfs(child);
        }
    }

    @SuppressWarnings("unchecked")
    public static void setReverseGraph() {
        reverseGraph = new ArrayList[graph.length];

        for (int i = 0; i < reverseGraph.length; i++) {
            reverseGraph[i] = new ArrayList<>();
        }

        for (int node = 0; node < graph.length; node++) {
            for (int child = 0; child < graph[node].size(); child++) {
                int parent = graph[node].get(child);
                reverseGraph[parent].add(node);
            }
        }
    }

}
