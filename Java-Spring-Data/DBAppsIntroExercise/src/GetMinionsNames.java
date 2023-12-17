import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetMinionsNames {

    private static final String GET_VILLAIN_BY_ID = "SELECT name FROM villains WHERE id = ?";
    private static final String GET_ALL_MINION_NAME_AND_AGE_FOR_A_VILLAIN =
            "SELECT DISTINCT m.name, m.age " +
            "FROM villains v " +
            "JOIN minions_villains mv on v.id = mv.villain_id " +
            "JOIN minions m on m.id = mv.minion_id " +
            "WHERE v.id = ?";

    public static void main(String[] args) throws IOException, SQLException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int villainId = Integer.parseInt(reader.readLine());

        Connection connection = Utility.getSQLConnections();
        ResultSet villainResultSet = getResultSet(connection, GET_VILLAIN_BY_ID, villainId);
        ResultSet minionResultSet = getResultSet(connection, GET_ALL_MINION_NAME_AND_AGE_FOR_A_VILLAIN, villainId);

        if (!villainResultSet.next()) {
            System.out.printf("No villain with ID %d exists in the database.%n", villainId);
            connection.close();
        } else {
            System.out.printf("Villain: %s%n", villainResultSet.getString("name"));
            for (int i = 1; minionResultSet.next(); i++) {
                System.out.printf("%d. %s %d%n",
                        i, minionResultSet.getString("name"), minionResultSet.getInt("age"));
            }
            connection.close();
        }
    }

    private static ResultSet getResultSet(Connection connection, String query, int villainId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, villainId);

        return statement.executeQuery();
    }
}













