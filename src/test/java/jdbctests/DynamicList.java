package jdbctests;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicList {
    String dbURL = "jdbc:oracle:thin:@3.86.235.137:1521:xe";
    String dbUsername ="hr";
    String dbPassword ="hr";

    @Test
    public void test2() throws SQLException {
        Connection connection = DriverManager.getConnection(dbURL,dbUsername,dbPassword);
        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery("SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, SALARY\n" +
//                "FROM EMPLOYEES\n" +  "WHERE ROWNUM < 6");

        String sql = "SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, SALARY\n" + "FROM EMPLOYEES\n" + "WHERE ROWNUM < 6";
        String sql1 = "SELECT * FROM EMPLOYEES";

        ResultSet resultSet = statement.executeQuery(sql);

        // in order to get column names we need resultSetMetaData
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        // list of maps to keep all information
        List<Map<String, Object>> queryData = new ArrayList<>();

        // number of columns
        int colCount = resultSetMetaData.getColumnCount();

        // loop through each row
        while (resultSet.next()){

            Map<String, Object> row = new HashMap<>();

            // some code to fill the dynamically
            for (int i = 1; i <= colCount ; i++) {
                row.put(resultSetMetaData.getColumnName(i), resultSet.getObject(i));
            }

            // add ready map row to the list
            queryData.add(row);

        }

        // print each ror inside the list
        for (Map<String, Object> row : queryData) {
            System.out.println(row.toString());
        }




        // CLOSE CONNECTIONS
        resultSet.close();
        statement.close();
        connection.close();

    }
}

/**
    Column value —> resultSet.getObject(i)
    Column name —> rsmd.getColumnName(i) I -> index starts from 1
    NumberOfColumns —> rsmd.getColumnCount();
    NumberOfRows —>  while(resultSet.next()){
                     }
 */