import java.util.*;

public class CyclesInGraph {

    private static Map<String, List<String>> graph = new HashMap<>();
    private static Set<String> visited = new HashSet<>();
    private static Set<String> cycles = new HashSet<>();
    private static boolean isGraphAcyclic = true;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String source = null;

        String line = scanner.nextLine();

        while (!"End".equals(line)) {
            String[] tokens = line.split("-");

            if (source == null) source = tokens[0];

            graph.putIfAbsent(tokens[0], new ArrayList<>());
            graph.putIfAbsent(tokens[1], new ArrayList<>());
            graph.get(tokens[0]).add(tokens[1]);

            line = scanner.nextLine();
        }

        dfs(source);
        if (visited.size() < graph.size()) isGraphAcyclic = false;

        if (isGraphAcyclic) {
            System.out.println("Acyclic: Yes");
        } else {
            System.out.println("Acyclic: No");
        }
    }

    private static void dfs(String source) {
        if (cycles.contains(source)) {
            isGraphAcyclic = false;
        }

        if (visited.contains(source)) {
            return;
        }

        cycles.add(source);
        visited.add(source);

        List<String> children = graph.get(source);

        for (String child : children) {
            dfs(child);
        }

        cycles.remove(source);
    }
}

