package jdbcTests;

import org.junit.jupiter.api.Test;
import utilities.DBUtils;

import java.sql.*;

public class LibraryConnectionTest {

    @Test
    public void test1() {
        // Database connection details
//        String dbURL = "jdbc:postgresql://localhost:5432/postgres";
//        String dbUsername = "postgres";
//        String dbPassword = "mysecretpassword";
//        String SCHEMA_NAME = "information_schema";      // Typically, user tables are in the public schema


        try {
            // Establishing connection to the PostgreSQL database
//            Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            Connection connection = DBUtils.createConnectionWithSchema();

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM books");

            // Moving a pointer to the first row
            resultSet.next();

            // Printing the value of the second column in the first row
            System.out.println(resultSet.getString(2));

            // Closing connection
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace(); // or handle the exception as needed
        }
    }

    @Test
    public void test2() {
        // Database connection details
        String dbURL = "jdbc:postgresql://localhost:5432/postgres";
        String dbUsername = "postgres";
        String dbPassword = "mysecretpassword";
        String schemaName = "information_schema";

        try {
            // Establishing connection to the PostgresSQL database
            Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

            // No need to implement a specific method to create and destroy connections for PostgreSQL
            // DBUtils.createConnection(dbUrl, dbUsername, dbPassword);
            // DBUtils.destroy();
            // Connection handling will be done directly in each method or through a separate utility if needed.
        } catch (SQLException e) {
            e.printStackTrace(); // or handle the exception as needed
        }
    }
}
