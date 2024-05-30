package jdbcTests.fastTrack_day02;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class P02_FlexibleNavigation {
    String dbURL = "jdbc:postgresql://localhost:5432/postgres";
    String dbUsername = "postgres";
    String dbPassword = "mysecretpassword";
    String schemaName = "information_schema";      // Typically, user tables are in the public schema

    @Test
    public void flexibleNavigation() throws SQLException {

        // DriverManager class will help us to create a connection with the help getConnection method
        Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        connection.setSchema(schemaName);
        // It helps us to execute queries
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

        // ResultSet Object will store data after execution.It stores only data
        ResultSet resultSet = statement.executeQuery("select FIRST_NAME,LAST_NAME from EMPLOYEES");

        //get me first row info
        resultSet.next();
        System.out.println(resultSet.getString(1)+" "+resultSet.getString(2));

        // get me second row
        resultSet.next();
        System.out.println(resultSet.getString(1)+" "+resultSet.getString(2));

        /*
        ResultSet.TYPE_SCROLL_INSENSITIVE--> To do flexible navigation
        ResultSet.CONCUR_READ_ONLY -->  This type of ResultSet Object is not updatable

         */


        //get me last row information
        // last() --> true if the cursor is on a valid row; false if there are no rows in the result set
        resultSet.last();
        System.out.println(resultSet.getString(1)+" "+resultSet.getString(2));


        // How many row we have ?
        // getRow -->  the current row number; 0 if there is no current row
        System.out.println("rs.getRow() = " + resultSet.getRow()); // 107

        // get data from row 8
        resultSet.absolute(8);
        System.out.println("rs.getRow() = " + resultSet.getRow()); // 8
        System.out.println(resultSet.getString(1)+" "+resultSet.getString(2));

        /*
        How can we get how many row we have
            rs.last();
            int rowNumber=rs.getRow();

         */
        //rs.previous -- > true if the cursor is now positioned on a valid row; false if the cursor is positioned before the first row
        resultSet.previous();   // 7
        System.out.println(resultSet.getString(1)+" "+resultSet.getString(2));

        // to print all data from the beginning, we need to move the cursor into beforeFirstRow
        // Moves the cursor to the front of this ResultSet object, just before the first row. This method has no effect if the result set contains no rows.
        resultSet.beforeFirst();


        // HOW TO PRINT ALL RESULT SET INFO
        System.out.println(" HOW TO PRINT ALL RESULT SET INFO ");
        while(resultSet.next()){

            System.out.println(resultSet.getRow()+"-"+resultSet.getString(1)+" "+resultSet.getString(2));

        }






        // CLOSE CONNECTION
        resultSet.close();
        statement.close();
        connection.close();
    }
}
