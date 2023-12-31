import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArticulationPoints {

    public static List<Integer>[] graph;
    public static List<Integer> points;
    public static boolean[] visited;
    public static int[] parents;
    public static int[] depths;
    public static int[] lowPoints;

    public static List<Integer> findArticulationPoints(List<Integer>[] targetGraph) {

        graph = targetGraph;
        points = new ArrayList<>();
        visited = new boolean[graph.length];
        parents = new int[graph.length];
        depths = new int[graph.length];
        lowPoints = new int[graph.length];

        Arrays.fill(parents, -1);

        discoverArticulationPoints(0, 1);

        return points;
    }

    private static void discoverArticulationPoints(int node, int depth) {
        visited[node] = true;
        depths[node] = depth;
        lowPoints[node] = depth;

        int childCount = 0;
        boolean isArticulation = false;

        for (int child : graph[node]) {
            if (!visited[child]) {
                parents[child] = node;
                discoverArticulationPoints(child, depth + 1);
                childCount++;

                if (lowPoints[child] >= depths[node]) {
                    isArticulation = true;
                }

                lowPoints[node] = Math.min(lowPoints[node], lowPoints[child]);

            } else if (parents[node] != child) {
                lowPoints[node] = Math.min(lowPoints[node], depths[child]);
            }
        }

        if ((parents[node] == -1 && childCount > 1) || (parents[node] != -1 && isArticulation)) {
            points.add(node);
        }
    }
}
