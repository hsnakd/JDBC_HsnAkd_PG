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
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM REGIONS");

        // once you set up connection default pointer looks for 0




        while (resultSet.next()){
            System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2));
        }

        resultSet.beforeFirst();

        ResultSetMetaData rsmd = resultSet.getMetaData();

        while (resultSet.next()){
            System.out.print(rsmd.getColumnName(1) + " - " + resultSet.getString(1));
            System.out.print(" / ");
            System.out.println(rsmd.getColumnName(2) + " - " + resultSet.getString(2));

        }


        resultSet.beforeFirst();
        int columnCount = rsmd.getColumnCount();

        System.out.println();

        for (int colIndex = 1; colIndex <=columnCount ; colIndex++) {
            System.out.print(rsmd.getColumnName(colIndex) + " \t");
        }

        System.out.println();

        while (resultSet.next()){
            for (int colIndex = 1; colIndex <= columnCount; colIndex++) {
                System.out.print(resultSet.getString(colIndex) + " \t" + " \t" + " \t");
            }
            System.out.println();
        }


        // CLOSE CONNECTIONS
        resultSet.close();
        statement.close();
        connection.close();
    }
}
