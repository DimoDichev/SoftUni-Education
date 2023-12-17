import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetVillainsNames {

    private static final String GET_ALL_VILLAINS_NAME_WHIT_MORE_THAN_15_MINIONS =
            "SELECT v.name, COUNT(DISTINCT mv.minion_id) AS 'count_minions' " +
            "FROM villains AS v " +
            "JOIN minions_villains mv on v.id = mv.villain_id " +
            "GROUP BY v.name " +
            "HAVING count_minions > 15 " +
            "ORDER BY count_minions DESC";

    public static void main(String[] args) throws SQLException {

        Connection connection = Utility.getSQLConnections();
        PreparedStatement stmt = connection.prepareStatement(GET_ALL_VILLAINS_NAME_WHIT_MORE_THAN_15_MINIONS);
        ResultSet results = stmt.executeQuery();

        while (results.next()) {
            System.out.printf("%s %d",
                    results.getString("name"),
                    results.getInt("count_minions"));
        }

        connection.close();
    }
}
