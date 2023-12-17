import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

public class DBAppIntro {

    private static String GET_COUNT_FOR_PLAYED_GAMES =
            "SELECT u.first_name, u.last_name, COUNT(ug.game_id) " +
            "FROM users AS u " +
            "JOIN users_games AS ug ON u.id = ug.user_id " +
            "WHERE user_name = ? " +
            "GROUP BY u.first_name, u.last_name";

    public static void main(String[] args) throws IOException, SQLException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Properties props = getProperties();

        String username = reader.readLine();

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/diablo", props);

        PreparedStatement stmt = connection.prepareStatement(GET_COUNT_FOR_PLAYED_GAMES);
        stmt.setString(1, username);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            System.out.println("User: " + username);
            System.out.printf("%s %s has played %d games",
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getInt(3));
        } else {
            System.out.println("No such user exists");
        }


    }

    private static Properties getProperties() {
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
        return props;
    }
}
