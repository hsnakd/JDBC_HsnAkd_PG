package jdbcTests;

import org.junit.jupiter.api.Test;
import utilities.DBUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DynamicList {

    @Test
    public void testDynamicList() throws SQLException {
        // PostgreSQL's connection information

        // DBUtils.createConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "mysecretpassword");

//        String dbURL = "jdbc:postgresql://localhost:5432/postgres";
//        String dbUsername = "postgres";
//        String dbPassword = "mysecretpassword";
//        String schemaName = "information_schema";  // Typically, user tables are in the public schema

        // Connect to Docker PostgreSQL container
//        DBUtils2.createConnection(dbURL, dbUsername, dbPassword);
        DBUtils.createConnectionWithSchema();

        // SQL query
        String sql = "SELECT table_name, table_schema\n" +
                "FROM tables\n" +
                "WHERE table_schema = 'information_schema'\n" +
                "LIMIT 5";



        // Retrieve query result
        List<Map<String, Object>> queryData = DBUtils.getQueryResultMap(sql);

        // Print the results
        for (Map<String, Object> row : queryData) {
            System.out.println(row.toString());
        }

        // Close the connection
        DBUtils.destroy();
    }
}
