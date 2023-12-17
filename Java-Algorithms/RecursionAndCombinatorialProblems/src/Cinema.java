import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Cinema {

    public static String[] seats;
    public static List<String> people;
    public static String[] combinations;
    public static boolean[] used;

    public static void main(String[] args) throws IOException {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));

        people = Arrays.stream(scanner.readLine().split(", ")).collect(Collectors.toList());
        seats = new String[people.size()];

        String commands = scanner.readLine();

        while (!commands.equals("generate")) {
            String name = commands.split(" - ")[0];
            int seatNumber = Integer.parseInt(commands.split(" - ")[1]);

            seats[seatNumber - 1] = name;
            people.remove(name);

            commands = scanner.readLine();
        }

        combinations = new String[people.size()];
        used = new boolean[people.size()];

        permute(0);
    }

    private static void permute(int index) {
        if (index == people.size()) {
            print();
        } else {
            for (int i = 0; i < people.size(); i++) {
                if (!used[i]) {
                    used[i] = true;
                    combinations[index] = people.get(i);
                    permute(index + 1);
                    used[i] = false;
                }
            }
        }
    }

    private static void print() {
        List<String> out = new ArrayList<>();
        int index = 0;
        for (String seat : seats) {
            if (seat != null) {
                out.add(seat);
            } else {
                out.add(combinations[index]);
                index++;
            }
        }
        System.out.println(String.join(" ", out));
    }
}
