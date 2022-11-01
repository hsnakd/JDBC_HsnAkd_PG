package jdbctests;

import java.sql.*;

public class TestConnectionLoop {
    public static void main(String[] args) throws SQLException {
//        In order to test Database using JAVA, First step we need to connect Database via
//        Connection String. It includes URL - Username - Password
//                      jdbc:DataBaseType:subprotocol:@Host:port:SID
        String dbURL = "jdbc:oracle:thin:@3.86.235.137:1521:xe";
        String dbUsername ="hr";
        String dbPassword ="hr";

        /**
        This 3 steps are important and all comes from import java.sql.*;
            Connection —> Helps our java project connect to database
            Statement —> Helps to write and execute SQL query
            ResultSet —> A data structure where we can store the data that came from database
        */

        Connection connection = DriverManager.getConnection(dbURL,dbUsername,dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM REGIONS");

//        // once you set up connection default pointer looks for 0
//
//
//        // next() ==> move pointer to first row
//        resultSet.next();
//
//        // getting information with column name
//        String result = resultSet.getString("REGION_NAME");
//        System.out.println("With Column Name : " + result);
//
//        // getting information with column index (starts 1)
//        String result2 = resultSet.getString(2);
//        System.out.println("With Index Number : " + result2);
//
//        // 1 - Europe
//        System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2));
//
//        // next() ==> move pointer to next row ==> 2
//        resultSet.next();
//
//        // 2 - Americas
//        System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2));
//
//        // next() ==> move pointer to next row ==> 3
//        resultSet.next();
//
//        // 3 - Asia
//        System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2));
//




        while (resultSet.next()){
            System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2));
        }



        // CLOSE CONNECTIONS
        resultSet.close();
        statement.close();
        connection.close();
    }
}
