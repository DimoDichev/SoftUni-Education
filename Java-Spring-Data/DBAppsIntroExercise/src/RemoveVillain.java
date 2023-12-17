import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RemoveVillain {

    private static final String GET_VILLAIN_NAME_BY_ID = "SELECT name FROM villains WHERE id = ?";
    private static final String DELETE_VILLAIN_BY_ID = "DELETE FROM villains WHERE id = ?";
    private static final String SELECT_MINION_COUNT_BY_VILLAIN_ID =
            "SELECT COUNT(minion_id) AS 'count_of_minion' " +
            "FROM minions_villains " +
            "WHERE villain_id = ?";
    private static final String DELETE_FROM_MINIONS_VILLAINS_BY_ID = "DELETE FROM minions_villains WHERE villain_id = ?";

    public static void main(String[] args) throws IOException, SQLException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int villainId = Integer.parseInt(reader.readLine());

        Connection connection = Utility.getSQLConnections();

        ResultSet rsVillainName = getResultSet(villainId, GET_VILLAIN_NAME_BY_ID, connection);

        if (!rsVillainName.next()) {
            System.out.println("No such villain was found");
            connection.close();
            return;
        }

        String villainName = rsVillainName.getString("name");

        ResultSet rsCountMinion = getResultSet(villainId, SELECT_MINION_COUNT_BY_VILLAIN_ID, connection);
        rsCountMinion.next();
        int countMinions = rsCountMinion.getInt("count_of_minion");

        connection.setAutoCommit(false);

        try {
            executeDeleteQuery(villainId, connection, DELETE_FROM_MINIONS_VILLAINS_BY_ID);
            executeDeleteQuery(villainId, connection, DELETE_VILLAIN_BY_ID);

            connection.commit();

            System.out.println(villainName + " was deleted");
            System.out.println(countMinions + " minions released");
        } catch (SQLException ex) {
            System.out.println(ex.getSQLState());
            connection.rollback();
        }

        connection.close();
    }

    private static void executeDeleteQuery(int id, Connection connection, String query) throws SQLException {
        PreparedStatement deleteStatement = connection.prepareStatement(query);
        deleteStatement.setInt(1, id);
        deleteStatement.executeUpdate();
    }

    private static ResultSet getResultSet(int villainId, String query, Connection connection) throws SQLException {
        PreparedStatement getVillainNameStmt = connection.prepareStatement(query);
        getVillainNameStmt.setInt(1, villainId);
        return getVillainNameStmt.executeQuery();
    }

}
