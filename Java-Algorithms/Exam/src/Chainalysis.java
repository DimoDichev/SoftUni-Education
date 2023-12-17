import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Chainalysis {

    private static Map<String, Set<String>> graph = new HashMap<>();

    private static Set<String> visited = new HashSet<>();
    private static int groupCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int transactionCount = Integer.parseInt(reader.readLine());

        for (int i = 0; i < transactionCount; i++) {
            String[] inputLine = reader.readLine().split("\\s+");

            String from = inputLine[0];
            String to = inputLine[1];

            graph.putIfAbsent(from, new HashSet<>());
            graph.putIfAbsent(to, new HashSet<>());
            graph.get(from).add(to);
        }

        for (String key : graph.keySet()) {
            if (!visited.contains(key)) {
                groupFinder(key);
                groupCount++;
            }
        }

        System.out.println(groupCount);
    }

    private static void groupFinder(String node) {
        visited.add(node);

        Set<String> children = graph.get(node);

        for (String child : children) {
            if (!visited.contains(child)) {
                groupFinder(child);
            }
        }
    }
}

// Result 80 of 100;