import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Salaries {

    private static List<List<Integer>> graph = new ArrayList<>();
    private static long[] salaries;
    private static boolean[] visited;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int employeesCount = Integer.parseInt(scanner.nextLine());

        int[] managerCount = new int[employeesCount];
        salaries = new long[employeesCount];
        visited = new boolean[employeesCount];

        for (int i = 0; i < employeesCount; i++) {
            String line = scanner.nextLine();

            graph.add(new ArrayList<>());

            for (int emp = 0; emp < line.length(); emp++) {
                char symbol = line.charAt(emp);

                if ('Y' == symbol) {
                    managerCount[emp]++;
                    graph.get(i).add(emp);
                }
            }
        }

        for (int node = 0; node < managerCount.length; node++) {
            if (managerCount[node] == 0) dfs(node);
        }

        System.out.println(Arrays.stream(salaries).sum());
    }

    private static void dfs(int node) {
        List<Integer> children = graph.get(node);

        if (visited[node]) return;

        visited[node] = true;

        for (int child : children) {
            if (!visited[child]) dfs(child);
        }

        long sum = salaries[node] = children.stream().mapToLong(e -> salaries[e]).sum();
        salaries[node] = sum == 0 ? 1 : sum;
    }
}
