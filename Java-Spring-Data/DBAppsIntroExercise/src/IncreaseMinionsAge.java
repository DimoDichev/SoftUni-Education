import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class IncreaseMinionsAge {

    private static final String MODIFY_MINIONS_DATA_BY_ID =
            "UPDATE minions " +
            "SET name = LOWER(name), age = age + 1 " +
            "WHERE id = ?";
    private static final String GET_ALL_MINIONS_NAME_AND_AGE = "SELECT name, age FROM minions";

    public static void main(String[] args) throws IOException, SQLException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] minionsIds = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        Connection connection = Utility.getSQLConnections();

        for (int i = 0; i < minionsIds.length; i++) {
            PreparedStatement modifyMinionDataStmt = connection.prepareStatement(MODIFY_MINIONS_DATA_BY_ID);
            modifyMinionDataStmt.setInt(1, minionsIds[i]);
            modifyMinionDataStmt.executeUpdate();
        }

        PreparedStatement getMinionsDataStmt = connection.prepareStatement(GET_ALL_MINIONS_NAME_AND_AGE);
        ResultSet minionsResultSet = getMinionsDataStmt.executeQuery();

        while (minionsResultSet.next()) {
            System.out.printf("%s %d%n",
                    minionsResultSet.getString("name"),
                    minionsResultSet.getInt("age"));
        }

        connection.close();
    }
}
