import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChangeTownNamesCasing {

    private static final String UPDATE_TOWN_NAME_TO_UPPERCASE = "UPDATE towns SET name = UPPER(name) WHERE country = ?";
    private static final String GET_TOWN_BY_TOWN_NAME = "SELECT * FROM towns where country = ?";

    public static void main(String[] args) throws IOException, SQLException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String country = reader.readLine();

        Connection connection = Utility.getSQLConnections();
        PreparedStatement stmt = connection.prepareStatement(UPDATE_TOWN_NAME_TO_UPPERCASE);
        stmt.setString(1, country);
        stmt.executeUpdate();

        stmt = connection.prepareStatement(GET_TOWN_BY_TOWN_NAME);
        stmt.setString(1, country);
        ResultSet resultSet = stmt.executeQuery();

        if (!resultSet.isBeforeFirst()) {
            System.out.println("No town names were affected.");
            return;
        }

        List<String> changedTowns = new ArrayList<>();

        while (resultSet.next()) {
            changedTowns.add(resultSet.getString("name"));
        }

        System.out.printf("%d town names were affected.%n", changedTowns.size());
        System.out.println(changedTowns);

        connection.close();
    }
}
