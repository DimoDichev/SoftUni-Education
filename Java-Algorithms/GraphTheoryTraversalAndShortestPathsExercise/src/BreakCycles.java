import java.util.*;

public class BreakCycles {

    private static Map<Character, List<Character>> graph = new HashMap<>();
    private static List<String> removedEdges = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.trim().isEmpty()) break;

            String[] tokens = line.split("\\s+");

            char node = tokens[0].charAt(0);

            graph.putIfAbsent(node, new ArrayList<>());

            for (int children = 2; children < tokens.length; children++) {
                char child = tokens[children].charAt(0);
                graph.putIfAbsent(child, new ArrayList<>());
                graph.get(node).add(child);
            }
        }

        List<Character> sortedNodes = new ArrayList<>(graph.keySet());
        Collections.sort(sortedNodes);

        for (Character source : sortedNodes) {
            List<Character> destinations = new ArrayList<>(graph.get(source));
            Collections.sort(destinations);

            for (Character destination : destinations) {
                graph.get(source).remove(destination);
                graph.get(destination).remove(source);

                if (hasCycle(source, destination)) {
                    removedEdges.add(String.format("%s - %s", source, destination));
                } else {
                    graph.get(source).add(destination);
                    graph.get(destination).add(source);
                }
            }
        }

        System.out.println("Edges to remove: " + removedEdges.size());
        removedEdges.forEach(System.out::println);
    }

    private static boolean hasCycle(Character source, Character destination) {
        Deque<Character> queue = new ArrayDeque<>();
        Set<Character> visited = new HashSet<>();

        queue.offer(source);
        visited.add(source);

        while (!queue.isEmpty()) {
            char node = queue.poll();
            if (destination.equals(node)) return true;

            List<Character> children = graph.get(node);

            for (Character child : children) {
                if (!visited.contains(child)) {
                    queue.offer(child);
                    visited.add(child);
                }
            }
        }

        return false;
    }
}
