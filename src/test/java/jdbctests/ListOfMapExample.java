package jdbctests;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListOfMapExample {
    // Database connection details
    String dbURL = "jdbc:postgresql://localhost:5432/postgres";
    String dbUsername = "postgres";
    String dbPassword = "mysecretpassword";
    String schemaName = "information_schema";  // Typically, user tables are in the public schema

    @Test
    public void test1() throws SQLException {

    // creating a list for keeping all the rows maps
        List<Map<String, Object>> queryData = new ArrayList<>();

        Map<String, Object> row1 = new HashMap<>();

        row1.put("first_name","Steven");
        row1.put("last_name","King");
        row1.put("salary","24000");
        row1.put("job_id","AD_PRES");

        System.out.println("row1 = " + row1.toString());

        Map<String, Object> row2 = new HashMap<>();

        row2.put("first_name","Neena");
        row2.put("last_name","Kochhar");
        row2.put("salary","17000");
        row2.put("job_id","AD_VP");

        System.out.println("row2 = " + row2.toString());

    // adding rows one by one to my list
        queryData.add(row1);
        queryData.add(row2);

    // get the Steven lastname directly from the list
        System.out.println(queryData.get(0).get("last_name"));
    // get the Neena salary directly from the list
        System.out.println(queryData.get(1).get("salary"));
    }

    @Test
    public void test2() throws SQLException {
        Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, SALARY " +
                "FROM " + schemaName + ".EMPLOYEES " +
                "LIMIT 5");

        // In order to get column names we need resultSetMetaData
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        // Creating list for keeping all the rows as maps
        List<Map<String, Object>> queryData = new ArrayList<>();

        // Iterate through result set and populate the list
        while (resultSet.next()) {
            Map<String, Object> row = new HashMap<>();
            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                row.put(resultSetMetaData.getColumnName(i), resultSet.getObject(i));
            }
            queryData.add(row);
        }

        // Print all rows
        for (Map<String, Object> row : queryData) {
            System.out.println(row.toString());
        }

        // Close connections
        resultSet.close();
        statement.close();
        connection.close();
    }
}
