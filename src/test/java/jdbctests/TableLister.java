package jdbctests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TableLister {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Load the JDBC driver
            Class.forName("org.postgresql.Driver");

            // Connect to the database
            String url = "jdbc:postgresql://localhost:5432/postgres";
            String user = "postgres";
            String password = "mysecretpassword";
            connection = DriverManager.getConnection(url, user, password);

            // Create a statement object
            statement = connection.createStatement();

            // Create the query
            String sql = "SELECT table_name FROM information_schema.tables WHERE table_schema = 'information_schema'";

            // Execute the query and get the result set
            resultSet = statement.executeQuery(sql);

            // Process the results
            while (resultSet.next()) {
                // Print each table name
                System.out.println(resultSet.getString("table_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Release resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
