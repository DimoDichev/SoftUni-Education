import java.util.*;

public class Molecules {

    private static final Map<Integer, Set<Integer>> graph = new LinkedHashMap<>();
    private static final Set<Integer> visited = new HashSet<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int moleculesCount = Integer.parseInt(scanner.nextLine());
        int connections = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= moleculesCount ; i++) {
            graph.put(i, new HashSet<>());
        }

        for (int i = 0; i < connections; i++) {
            int[] connection = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int from = connection[0];
            int to = connection[1];

            graph.get(from).add(to);
        }

        int source = Integer.parseInt(scanner.nextLine());

        findNotConnectedMolecule(source);

        for (Integer molecule : graph.keySet()) {
            if (!visited.contains(molecule)) {
                System.out.print(molecule + " ");
            }
        }
    }

    private static void findNotConnectedMolecule(int source) {
        visited.add(source);

        Set<Integer> children = graph.get(source);

        for (Integer child : children) {
            if (!visited.contains(child)) {
                findNotConnectedMolecule(child);
            }
        }
    }
}
