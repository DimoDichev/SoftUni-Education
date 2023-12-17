import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AddMinion {

    private static final String GET_TOWN_ID_BY_NAME = "SELECT id FROM towns WHERE name = ?";
    private static final String INSERT_INTO_TOWNS = "INSERT INTO towns(name) VALUES(?)";
    private static final String TOWN_ADDED_FORMAT = "Town %s was added to the database.%n";
    private static final String GET_VILLAIN_ID_BY_NAME = "SELECT id FROM villains WHERE name = ?";
    private static final String INSERT_INTO_VILLAINS = "INSERT INTO villains(name, evilness_factor) VALUES(?, ?)";
    private static final String VILLAIN_ADDED_FORMAT = "Villain %s was added to the database.%n";
    private static final String GET_LAST_MINION_ID = "SELECT id FROM minions ORDER BY id DESC LIMIT 1";
    private static final String INSERT_INTO_MINIONS = "INSERT INTO minions(name, age, town_id) VALUES(?, ?, ?)";
    private static final String INSERT_INTO_MINIONS_VILLAINS =
            "INSERT INTO minions_villains(minion_id, villain_id) VALUES(?, ?)";
    private static final String EVILNESS_FACTOR = "evil";

    public static void main(String[] args) throws IOException, SQLException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] minionData = reader.readLine().substring(8).split("\\s+");

        String minionNameInput = minionData[0];
        int minionAgeInput = Integer.parseInt(minionData[1]);
        String minionTownInput = minionData[2];

        String villainNameInput = reader.readLine().substring(9);

        Connection connection = Utility.getSQLConnections();

        int townId = getId(connection, List.of(minionTownInput),
                GET_TOWN_ID_BY_NAME, INSERT_INTO_TOWNS, TOWN_ADDED_FORMAT);

        int villainId = getId(connection, List.of(villainNameInput, EVILNESS_FACTOR),
                GET_VILLAIN_ID_BY_NAME, INSERT_INTO_VILLAINS, VILLAIN_ADDED_FORMAT);

        PreparedStatement insertMinionStmt = connection.prepareStatement(INSERT_INTO_MINIONS);
        insertMinionStmt.setString(1, minionNameInput);
        insertMinionStmt.setInt(2, minionAgeInput);
        insertMinionStmt.setInt(3, townId);
        insertMinionStmt.executeUpdate();

        PreparedStatement selectLastMinionStmt = connection.prepareStatement(GET_LAST_MINION_ID);
        ResultSet lastMinionResultSet = selectLastMinionStmt.executeQuery();
        lastMinionResultSet.next();

        int minionId = lastMinionResultSet.getInt("id");

        PreparedStatement insertMinionVillainsStmt = connection.prepareStatement(INSERT_INTO_MINIONS_VILLAINS);
        insertMinionVillainsStmt.setInt(1, minionId);
        insertMinionVillainsStmt.setInt(2, villainId);
        insertMinionVillainsStmt.executeUpdate();

        System.out.printf("Successfully added %s to be minion of %s.%n", minionNameInput, villainNameInput);

        connection.close();
    }

    private static int getId(Connection connection, List<String> arguments,
                             String selectQuery, String insertQuery,
                             String printFormat) throws SQLException {

        String name = arguments.get(0);

        PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
        selectStmt.setString(1, name);

        ResultSet resultSet = selectStmt.executeQuery();

        if (!resultSet.next()) {
            PreparedStatement insertStmt = connection.prepareStatement(insertQuery);

            for (int index = 1; index <= arguments.size(); index++) {
                insertStmt.setString(index, arguments.get(index - 1));
            }

            insertStmt.executeUpdate();

            ResultSet newResultSet = selectStmt.executeQuery();
            newResultSet.next();

            System.out.printf(printFormat, name);

            return newResultSet.getInt("id");
        }

        return resultSet.getInt("id");
    }
}
