import java.util.*;

public class DistanceBetweenVertices {

    public static int[][] graph;
    public static Map<Integer, Integer> indexMapper = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int vertices = Integer.parseInt(scanner.nextLine());
        int pairs = Integer.parseInt(scanner.nextLine());

        graph = new int[vertices + 1][];

        for (int i = 1; i < vertices + 1; i++) {
            String[] line = scanner.nextLine().split(":");
            int key = Integer.parseInt(line[0]);

            indexMapper.put(key, i);

            if (line.length == 1) {
                graph[i] = new int[0];
                continue;
            }

            graph[i] = Arrays.stream(line[1].split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        for (int i = 0; i < pairs; i++) {
            String[] line = scanner.nextLine().split("-");

            int source = Integer.parseInt(line[0]);
            int destination = Integer.parseInt(line[1]);

            System.out.printf("{%d, %d} -> ", source, destination);

            int[] prev = new int[graph.length];
            Arrays.fill(prev, -1);

            bfs(indexMapper.get(source), indexMapper.get(destination), prev);

            List<Integer> path = new ArrayList<>();

            int parent = prev[indexMapper.get(destination)];

            while (parent != -1) {
                path.add(parent);
                parent = prev[parent];
            }

            int size = path.isEmpty() ? -1 : path.size();

            System.out.println(size);
        }

    }

    private static void bfs(int source, int destination, int[] prev) {
        Deque<Integer> queue = new ArrayDeque<>();

        queue.offer(source);

        boolean[] visited = new boolean[graph.length + 1];
        visited[source] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            if (node == destination) {
                return;
            }

            for (int i = 0; i < graph[node].length; i++) {
                int child = indexMapper.get(graph[node][i]);
                if (!visited[child]) {
                    prev[child] = node;
                    visited[child] = true;
                    queue.offer(child);
                }
            }
        }

    }

}