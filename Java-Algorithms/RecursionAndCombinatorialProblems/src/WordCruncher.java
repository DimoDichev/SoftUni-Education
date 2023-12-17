import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class WordCruncher {

    public static List<String> words;
    public static List<String> combinations = new ArrayList<>();
    public static String target;
    public static Map<Integer, List<String>> table = new HashMap<>();
    public static Map<String, Integer> occurrences = new HashMap<>();
    public static Set<String> out = new TreeSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        words = Arrays.stream(reader.readLine().split(", ")).collect(Collectors.toList());
        target = reader.readLine();

        words.removeIf(w -> !target.contains(w));

        for (String substring : words) {
            occurrences.putIfAbsent(substring, 0);
            occurrences.put(substring, occurrences.get(substring) + 1);

            int index = target.indexOf(substring);
            while (index != -1) {
                table.putIfAbsent(index, new ArrayList<>());
                table.get(index).add(substring);
                index = target.indexOf(substring, index + 1);
            }
        }

        permute(0);

        for (String str : out) {
            System.out.println(str);
        }
    }

    private static void permute(int index) {
        if (index == target.length()) {
            print();
        } else if (table.containsKey(index)) {
            List<String> strings = table.get(index);
            for (String string : strings) {
                if (occurrences.get(string) > 0) {
                    occurrences.put(string, occurrences.get(string) - 1);
                    combinations.add(string);
                    permute(index + string.length());
                    combinations.remove(combinations.size() - 1);
                    occurrences.put(string, occurrences.get(string) + 1);
                }
            }
        }
    }

    private static void print() {
        String output = String.join("", combinations);
        if (output.contains(target)) {
            out.add(String.join(" ", combinations));
        }
    }
}
