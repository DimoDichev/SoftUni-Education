import java.sql.*;

public class PrintAllMinionNames {

    private static final String GET_ALL_MINIONS = "SELECT name FROM minions";

    public static void main(String[] args) throws SQLException {

        Connection connection = Utility.getSQLConnections();
        PreparedStatement statement = connection.prepareStatement(GET_ALL_MINIONS,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery();

        int minionsCount = 0;

        while (resultSet.next()) {
            minionsCount++;
        }
        resultSet.beforeFirst();

        int firstIndex = 1;
        int lastIndex = minionsCount;

        for (int i = 0; i < minionsCount; i++) {
            if (i % 2 == 0) {
                resultSet.absolute(firstIndex);
                firstIndex++;
            } else {
                resultSet.absolute(lastIndex);
                lastIndex--;
            }

            System.out.println(resultSet.getString("name"));
        }

        connection.close();
    }
}
