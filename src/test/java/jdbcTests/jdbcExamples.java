package jdbcTests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.DBUtils; // Import the DBUtils class

import java.sql.*;

public class jdbcExamples {
//    String dbURL = "jdbc:postgresql://localhost:5432/postgres";
//    String dbUsername ="postgres";
//    String dbPassword ="mysecretpassword";
//    String schemaName = "information_schema";  // Typically, user tables are in the public schema

    @Test
    public void test1() throws SQLException {

        // Create a connection to PostgreSQL using DBUtils
//        DBUtils2.createConnection(dbURL, dbUsername, dbPassword);
        DBUtils.createConnectionWithSchema();
        Connection connection = DBUtils.getConnection(); // Get the connection object
        Statement statement = connection.createStatement(); // Create a statement object

        ResultSet resultSet = statement.executeQuery("SELECT * FROM DEPARTMENTS");

        // Iterate through the result set and print the data
        while (resultSet.next()){
            System.out.println(resultSet.getInt(1) + " - "
                    + resultSet.getString(2) + " - "
                    + resultSet.getInt(3) + " - "
                    + resultSet.getInt(4));
        }

        resultSet = statement.executeQuery("SELECT * FROM REGIONS");

        // Iterate through the result set and print the data
        while (resultSet.next()){
            System.out.println(resultSet.getInt(1) + " - "
                    + resultSet.getString(2));
        }

        // Close connections
        DBUtils.destroy();
    }

    @DisplayName("ResultSet Methods")
    @Test
    public void test2() throws SQLException {

        // Create a connection to PostgreSQL using DBUtils
//        DBUtils2.createConnection(dbURL, dbUsername, dbPassword);
        DBUtils.createConnectionWithSchema();
        Connection connection = DBUtils.getConnection(); // Get the connection object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet resultSet = statement.executeQuery("SELECT * FROM DEPARTMENTS");

        resultSet.last(); // Move the cursor to the last row
        int rowCount = resultSet.getRow(); // Get the row count
        System.out.println("Row Count: " + rowCount);

        resultSet.beforeFirst(); // Move the cursor before the first row

        // Iterate through the result set and print the data
        while (resultSet.next()){
            System.out.println(resultSet.getString(2));
        }

        // Close connections
        DBUtils.destroy();
    }

    @DisplayName("ResultSet Metadata")
    @Test
    public void test3() throws SQLException {
        // Create a connection to PostgreSQL using DBUtils
//        DBUtils.createConnection(dbURL, dbUsername, dbPassword);
        DBUtils.createConnectionWithSchema();
        Connection connection = DBUtils.getConnection(); // Get the connection object
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM DEPARTMENTS");

        // Get metadata about the database and print it
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        System.out.println("Database User Name: " + databaseMetaData.getUserName());
        System.out.println("Database Product Name: " + databaseMetaData.getDatabaseProductName());
        System.out.println("Database Product Version: " + databaseMetaData.getDatabaseProductVersion());
        System.out.println("Driver Name: " + databaseMetaData.getDriverName());
        System.out.println("Driver Version: " + databaseMetaData.getDriverVersion());

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        int colCount = resultSetMetaData.getColumnCount();
        System.out.println("Number of Columns: " + colCount);

        // Iterate through the result set metadata and print column names
        for (int i = 1; i <= colCount; i++) {
            System.out.println("Column Name: " + resultSetMetaData.getColumnName(i));
        }

        // Close connections
        DBUtils.destroy();
    }
}
