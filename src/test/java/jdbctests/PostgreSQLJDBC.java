package jdbctests;

import utilities.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class PostgreSQLJDBC {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String schemaName = "information_schema";  // Typically, user tables are in the public schema

        try {
            // Create connection to the PostgreSQL database
//            connection = DBUtils.createConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "mysecretpassword");
            connection = DBUtils.createConnection();
            connection.setSchema(schemaName);

            // Create a statement to execute queries
            statement = connection.createStatement();
            String sql = "SELECT * FROM employees"; // Change the query according to your database schema
            resultSet = statement.executeQuery(sql);

            // Process the results
            while (resultSet.next()) {
                // Example: Print the "first_name" column from each row
                System.out.println(resultSet.getString("first_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Release resources and close the connection
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                DBUtils.destroy(); // Close the connection
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
