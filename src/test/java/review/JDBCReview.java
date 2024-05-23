package review;

import java.sql.*;

public class JDBCReview {

    public static void main(String[] args) throws SQLException {

        // Connection information for PostgreSQL database
        String dbURL = "jdbc:postgresql://localhost:5432/postgres";
        String dbUsername = "postgres";
        String dbPassword = "mysecretpassword";
        String schemaName = "information_schema";

        // Connection, statement, and result set objects
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Connect to the PostgreSQL database
            connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

            // Create SQL statement
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            // Execute query to retrieve data from the EMPLOYEES table
            resultSet = statement.executeQuery("SELECT * FROM " + schemaName + ".EMPLOYEES");

            /*
            When you want to get data from one row, your cursor should be on that row
            */

            // Move cursor to the first row
            resultSet.next();
            // Print data from specific columns
            System.out.println(resultSet.getString(1));
            System.out.println(resultSet.getString(5));
            System.out.println(resultSet.getString(7));
            System.out.println(resultSet.getString("email"));

            // Move cursor to the next row
            resultSet.next();
            // Get data from specific columns and print full name
            String firstName = resultSet.getString(2);
            String lastName = resultSet.getString(3);
            System.out.println("fullName = " + firstName + " " +  lastName);

            // Creating result set meta data
            ResultSetMetaData rsmd = resultSet.getMetaData();

            // Print column count and column names
            System.out.println(rsmd.getColumnCount());
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.println(rsmd.getColumnName(i));
            }

            // Print column type of the first column
            System.out.println("rsmd.getColumnType(1) = " + rsmd.getColumnType(1));

            // Get database meta data
            DatabaseMetaData dbmd = connection.getMetaData();

            // Print database user name, driver name, and database product name
            System.out.println(dbmd.getUserName());
            System.out.println(dbmd.getDriverName());
            System.out.println(dbmd.getDatabaseProductName());

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
