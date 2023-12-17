import java.util.*;
import java.util.Scanner;

public class Guards {

    private static final Map<Integer, Set<Integer>> graph = new HashMap<>();
    private static final Set<Integer> visited = new HashSet<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int nodesCount = Integer.parseInt(scanner.nextLine());
        int edgesCount = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= nodesCount; i++) {
            graph.put(i, new HashSet<>());
        }

        for (int i = 0; i < edgesCount; i++) {
            int[] input = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int from = input[0];
            int to = input[1];

            graph.get(from).add(to);
        }

        int startNode = Integer.parseInt(scanner.nextLine());

        findUnreachableCity(startNode);

        graph.keySet().forEach(e -> {
            if (!visited.contains(e)) {
                System.out.print(e + " ");
            }
        });
    }

    private static void findUnreachableCity(int startNode) {
        visited.add(startNode);

        Set<Integer> children = graph.get(startNode);

        for (Integer child : children) {
            if (!visited.contains(child)) {
                findUnreachableCity(child);
            }
        }
    }
}
