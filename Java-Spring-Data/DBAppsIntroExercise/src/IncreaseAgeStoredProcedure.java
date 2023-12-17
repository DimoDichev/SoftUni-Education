import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class IncreaseAgeStoredProcedure {

    private static final String INCREASE_MINION_AGE_BY_ID_USP = "call usp_get_older(?)";
    private static final String GET_MINION_NAME_AND_AGE_BY_ID = "SELECT name, age FROM minions WHERE id = ?";

    public static void main(String[] args) throws IOException, SQLException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int minionId = Integer.parseInt(reader.readLine());

        Connection connection = Utility.getSQLConnections();

        CallableStatement getOlderStmt = connection.prepareCall(INCREASE_MINION_AGE_BY_ID_USP);
        getOlderStmt.setInt(1, minionId);
        getOlderStmt.execute();

        PreparedStatement getMinionNameAgeStmt = connection.prepareStatement(GET_MINION_NAME_AND_AGE_BY_ID);
        getMinionNameAgeStmt.setInt(1, minionId);
        ResultSet minionResultSet = getMinionNameAgeStmt.executeQuery();
        minionResultSet.next();

        System.out.println(minionResultSet.getString("name") + " " + minionResultSet.getInt("age"));

        connection.close();
    }
}
