package jdbctests;

import java.sql.*;

public class TestConnectionLoop {
    public static void main(String[] args) {
        // Connection information for PostgreSQL database
        String dbURL = "jdbc:postgresql://localhost:5432/postgres";
        String dbUsername = "postgres";
        String dbPassword = "mysecretpassword";

        // Connection, statement, and result set objects
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Connect to the PostgreSQL database
            connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

            // Create SQL statement
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            // Fetch data from the "regions" table
            resultSet = statement.executeQuery("SELECT * FROM regions");

            // Loop through the result set
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("region_id") + " - " + resultSet.getString("region_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close result set, statement, and connection
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
