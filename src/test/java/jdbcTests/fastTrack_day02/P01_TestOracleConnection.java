package jdbcTests.fastTrack_day02;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class P01_TestOracleConnection {
    String dbURL = "jdbc:postgresql://localhost:5432/postgres";
    String dbUsername = "postgres";
    String dbPassword = "mysecretpassword";
    String schemaName = "information_schema";      // Typically, user tables are in the public schema


    @Test
    public void testConnection() throws SQLException {

        // DriverManager class will help us to create a connection with the help getConnection method
        Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        connection.setSchema(schemaName);

        // It helps us to execute queries
        Statement statement = connection.createStatement();

        // ResultSet Object will store data after execution.It stores only data
        ResultSet resultSet = statement.executeQuery("select * from employees");

        // If we want to get data from a database we are going to implement in here
        /*
        // GET ME FIRST ROW REGION ID
        // next() --> true if the new current row is valid; false if there are no more rows
        rs.next();
        System.out.println(" FIRST ROW INFO ");

        // COLUMNINDEX + COLUMNLABEL
        System.out.println("rs.getString(1) = " + rs.getString(1));
        System.out.println("rs.getString(\"REGION_ID\") = " + rs.getString("REGION_ID"));

        System.out.println("rs.getInt(1) = " + rs.getInt(1));
        System.out.println("rs.getInt(\"REGION_ID\") = " + rs.getInt("REGION_ID"));

        // GET ME FIRST ROW REGION_NAME
        System.out.println("rs.getString(2) = " + rs.getString(2));
        System.out.println("rs.getString(\"REGION_NAME\") = " + rs.getString("REGION_NAME"));


        // SECOND ROW  2 - Americas
        rs.next();
        System.out.println(" SECOND ROW INFO ");

        System.out.println(rs.getString(1)+" - "+rs.getString(2));

        // THIRD ROW  3 - Asia
        rs.next();
        System.out.println(" THIRD ROW INFO ");
        System.out.println(rs.getString(1)+" - "+rs.getString(2));

        // FOURTH ROW  4 - Middle East and Africa
        rs.next();
        System.out.println(" FOURTH ROW INFO ");
        System.out.println(rs.getString(1)+" - "+rs.getString(2));

         */


        // What if we have 1000 line in resultSet ?

        while(resultSet.next()){

            System.out.println("----------------------------------------------------------------");
            System.out.println(resultSet.getString(1) + " - " + resultSet.getString(2));

        }



        // CLOSE CONNECTION
        resultSet.close();
        statement.close();
        connection.close();
    }
}
