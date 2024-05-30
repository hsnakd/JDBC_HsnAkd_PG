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
            connection.setSchema(schemaName);

            // Create SQL statement
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            // Execute a query to retrieve data from the EMPLOYEES table
            resultSet = statement.executeQuery("SELECT * FROM EMPLOYEES");

            /*
            When you want to get data from one row, your cursor should be on that row
            */

            System.out.println("---------------------------- First Employee ----------------------------");

            // Move the cursor to the first row
            resultSet.next();
            // Print data from specific columns
            System.out.println(resultSet.getString(1));
            System.out.println(resultSet.getString(5));
            System.out.println(resultSet.getString(7));
            System.out.println(resultSet.getString("email"));

            System.out.println(resultSet.getString("first_name"));      // first_name
            System.out.println(resultSet.getString(2));                 // first_name

            System.out.println("---------------------------- Second Employee ----------------------------");
            // Move the cursor to the next row
            resultSet.next();
            // Get data from specific columns and print full name
            String firstName = resultSet.getString(2);
            String lastName = resultSet.getString(3);
            System.out.println("fullName = " + firstName + " " +  lastName);

            System.out.println("---------------------------- ResultSetMetaData ----------------------------");

            // Creating result set meta data
            ResultSetMetaData rsmd = resultSet.getMetaData();

            System.out.println("---------------------------- Print column count and column names ----------------------------");

            // Print column count and column names
            System.out.println("rsmd.getColumnCount() = " + rsmd.getColumnCount());
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.print( i + ". Column : ");
                System.out.println(rsmd.getColumnName(i));
            }

            System.out.println("---------------------------- Print column type of the first column ----------------------------");

            // Print column type of the first column
            System.out.println("rsmd.getColumnType(1) = " + rsmd.getColumnType(1));
            System.out.println("rsmd.getColumnType(2) = " + rsmd.getColumnType(2));
            System.out.println("rsmd.getColumnType(3) = " + rsmd.getColumnType(3));


            // Get database meta data
            DatabaseMetaData dbmd = connection.getMetaData();

            System.out.println("---------------------------- Print database username, driver name, and database product name ---------------------------- ");

            // Print database username, driver name, and database product name
            System.out.println("dbmd.getURL() = " + dbmd.getURL());
            System.out.println("dbmd.getUserName() = " + dbmd.getUserName());
            System.out.println("dbmd.getConnection() = " + dbmd.getConnection());
            System.out.println("dbmd.getTableTypes() = " + dbmd.getTableTypes());
            System.out.println("dbmd.getDriverName() = " + dbmd.getDriverName());
            System.out.println("dbmd.getDriverVersion() = " + dbmd.getDriverVersion());
            System.out.println("dbmd.getDatabaseProductName() = " + dbmd.getDatabaseProductName());
            System.out.println("dbmd.getDatabaseProductVersion() = " + dbmd.getDatabaseProductVersion());
            System.out.println("dbmd.getSchemas() = " + dbmd.getSchemas());
            System.out.println("dbmd.getSchemaTerm() = " + dbmd.getSchemaTerm());


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
