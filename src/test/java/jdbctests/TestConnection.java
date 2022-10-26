package jdbctests;

import java.sql.*;

public class TestConnection {
    public static void main(String[] args) throws SQLException {
//        In order to test Database using JAVA, First step we need to connect Database via
//        Connection String. It includes URL - Username - Password
        String dbURL = "jdbc:oracle:thin:@3.86.235.137:1521:xe";
        String dbUsername ="hr";
        String dbPassword ="hr";

//        This 3 steps are important and all comes from import java.sql.*;
//        Connection —> Helps our java project connect to database
//        Statement —> Helps to write and execute SQL query
//        ResultSet —> A data structure where we can store the data that came from database

        Connection connection = DriverManager.getConnection(dbURL,dbUsername,dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM REGIONS");


        // next() ==> move pointer to first row
        resultSet.next();

        // getting information with column name
        String result = resultSet.getString("REGION_NAME");
        System.out.println("result = " + result);

        // getting information with column index (starts 1)
        int resultInt = resultSet.getInt(1);
        System.out.println("result = " + resultInt);

        // CLOSE CONNECTIONS
        resultSet.close();
        statement.close();
        connection.close();
    }
}
