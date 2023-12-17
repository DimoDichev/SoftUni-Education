import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<List<Integer>> graph = new ArrayList<>();

        int linesCount = Integer.parseInt(reader.readLine());

        for (int i = 0; i < linesCount; i++) {
            String line = reader.readLine();

            if (line.trim().equals("")) {
                graph.add(new ArrayList<>());
                continue;
            }

            List<Integer> current = Arrays.stream(line.split("\\s+"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            graph.add(current);
        }

        List<Deque<Integer>> connectedComponents = getConnectedComponents(graph);

        for (Deque<Integer> children : connectedComponents) {
            for (int child : children) {
                System.out.print(child + " ");
            }
            System.out.println();
        }
    }

    public static List<Deque<Integer>> getConnectedComponents(List<List<Integer>> graph) {
        List<Deque<Integer>> components = new ArrayList<>();
        boolean[] visited = new boolean[graph.size()];

        for (int startNode = 0; startNode < graph.size(); startNode++) {
            if (!visited[startNode]) {
                components.add(new ArrayDeque<>());
                dfs(startNode, components, visited, graph);
            }
        }
        return components;
    }

    private static void dfs(int node, List<Deque<Integer>> components, boolean[] visited, List<List<Integer>> graph) {
        if (!visited[node]) {
            visited[node] = true;
            for (int child : graph.get(node)) {
                dfs(child, components, visited, graph);
            }
            components.get(components.size() - 1).offer(node);
        }
    }

    public static Collection<String> topSort(Map<String, List<String>> graph) {
        List<String> sorted = new ArrayList<>();
        Map<String, Integer> predecessorCount = predecessorCount(graph);

        while (!graph.isEmpty()) {
            String nodeToRemove = graph.keySet()
                    .stream()
                    .filter(e -> predecessorCount.get(e) == 0)
                    .findFirst()
                    .orElse(null);

            if (nodeToRemove == null) {
                break;
            }

            for (String child : graph.get(nodeToRemove)) {
                predecessorCount.put(child, predecessorCount.get(child) - 1);
            }

            sorted.add(nodeToRemove);
            graph.remove(nodeToRemove);
        }

        if(!graph.isEmpty()) {
            throw  new IllegalArgumentException();
        }

        return sorted;
    }

    private static Map<String, Integer> predecessorCount(Map<String, List<String>> graph) {
        Map<String, Integer> predecessorCount = new LinkedHashMap<>();

        for (Map.Entry<String, List<String>> node : graph.entrySet()) {
            predecessorCount.putIfAbsent(node.getKey(), 0);
            for (String child : node.getValue()) {
                predecessorCount.putIfAbsent(child, 0);
                predecessorCount.put(child, predecessorCount.get(child) + 1);
            }
        }

        return predecessorCount;
    }


}








