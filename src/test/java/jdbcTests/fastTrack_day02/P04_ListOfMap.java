package jdbcTests.fastTrack_day02;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P04_ListOfMap {

    String dbURL = "jdbc:postgresql://localhost:5432/postgres";
    String dbUsername = "postgres";
    String dbPassword = "mysecretpassword";
    String schemaName = "information_schema";      // Typically, user tables are in the public schema

    @Test
    public void listOfMap() throws SQLException {

        // DriverManager class will help us to create connection with the help getConnection method
        Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        connection.setSchema(schemaName);

        // It helps us to execute queries
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        // ResultSet Object will store data after execution.It stores only data
        ResultSet rs = statement.executeQuery("select * from REGIONS");

        // ResultSetMetaData
        ResultSetMetaData rsmd = rs.getMetaData();


        // Create a list to put maps for each row
        List<Map<String,Object>> dataList=new ArrayList<>();

        //iterate each row
        while(rs.next()){
            // store data for that row into Map
            Map<String,Object> rowMap=new HashMap<>();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                rowMap.put(rsmd.getColumnName(i),rs.getString(i));
            }

            dataList.add(rowMap);

        }

        System.out.println("PRINT ALL DATA FROM ANY QUERY");
        for (Map<String, Object> eachRow : dataList) {
            System.out.println(eachRow);
        }



        rs.close();
        statement.close();
        connection.close();

    }
}
