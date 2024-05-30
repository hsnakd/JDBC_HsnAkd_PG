package jdbcTests;

import utilities.DBUtils;

import java.sql.*;

public class TestConnection {
    public static void main(String[] args) throws SQLException {
        // Database connection details
        String dbURL = "jdbc:postgresql://localhost:5432/postgres";
        String dbUsername = "postgres";
        String dbPassword = "mysecretpassword";
        String schemaName = "information_schema";  // Typically, user tables are in the public schema

        // Establish the connection
//        Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
//        connection.setSchema(schemaName);
        //            connection = DBUtils.createConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "mysecretpassword");
        Connection connection = DBUtils.createConnectionWithSchema();

        // Create a Statement
        Statement statement = connection.createStatement();


        // Execute the query
        ResultSet resultSet = statement.executeQuery("SELECT * FROM regions");

        // Move the pointer to the first row
        resultSet.next();

        // Retrieve and print information using column name
        String result = resultSet.getString("region_name");
        System.out.println("With Column Name : " + result);

        // Retrieve and print information using column index (starting from 1)
        String result2 = resultSet.getString(2);
        System.out.println("With Index Number : " + result2);

        // Print the first row data
        System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2));

        // Move to the next row and print its data
        resultSet.next();
        System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2));

        // Move to the next row and print its data
        resultSet.next();
        System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2));

        // Close connections
        resultSet.close();
        statement.close();
        connection.close();
    }
}
