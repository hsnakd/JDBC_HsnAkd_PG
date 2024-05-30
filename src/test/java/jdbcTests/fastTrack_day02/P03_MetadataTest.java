package jdbcTests.fastTrack_day02;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class P03_MetadataTest {
    String dbURL = "jdbc:postgresql://localhost:5432/postgres";
    String dbUsername = "postgres";
    String dbPassword = "mysecretpassword";
    String schemaName = "information_schema";      // Typically, user tables are in the public schema
    @Test
    public void metadataTest() throws SQLException {

        // DriverManager class will help us to create a connection with the help getConnection method
        Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        connection.setSchema(schemaName);
        // It helps us to execute queries
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

        // ResultSet Object will store data after execution.It stores only data
        ResultSet resultSet = statement.executeQuery("select FIRST_NAME,LAST_NAME,SALARY from EMPLOYEES");


        // Database MetaData --> It is information about the database itself
        DatabaseMetaData dbMetaData = connection.getMetaData();
        System.out.println("dbMetaData.getUserName() = " + dbMetaData.getUserName());
        System.out.println("dbMetaData.getDriverVersion() = " + dbMetaData.getDriverVersion());
        System.out.println("dbMetaData.getDriverName() = " + dbMetaData.getDriverName());
        System.out.println("dbMetaData.getDatabaseProductName() = " + dbMetaData.getDatabaseProductName());


        // ResultSet MetaData --> It provides information about table upper side (columnName or columnCount)
        ResultSetMetaData rsmd = resultSet.getMetaData();
        
        // how many columns we have?
        int columnCount = rsmd.getColumnCount();
        System.out.println("columnCount = " + columnCount);

        // what is the name of the second column?
        System.out.println("rsmd.getColumnName(2) = " + rsmd.getColumnName(2));


        // Can we get all column names?
        System.out.println(" ALL COLUMN NAMES ");
        for (int i = 1; i <= columnCount; i++) {
            System.out.println("rsmd.getColumnName(i) = " + rsmd.getColumnName(i));
        }


        /*
        HOW TO GET DATA DYNAMICALLY FROM ANY QUERY?

            ResulSet --> it holds table data

                rs.next() --> to iterate each row dynamically

                rs.getString(columnIndex) --> will give info about this cell
                rs.getString(columnLabel)


            ResultSetMetaData --> it hols table info (columnCount / ColumnName)

                rsmd.getColumnCount() ---> will give how many column we have
                rsmd.getColumnName(int columnIndex) --> will give column Name


         */

        System.out.println(" ---- PRINT ALL DATA DYNAMICALLY ----- ");
        // FIRST_NAME - Steven  LAST_NAME - King SALARY - 24000.00
        // FIRST_NAME - Neena  LAST_NAME - Kocchar SALARY - 17000.00

        // iterate each row
        while(resultSet.next()){
            // iterate each column
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(rsmd.getColumnName(i)+"-"+resultSet.getString(i)+" ");
            }
            System.out.println();
        }


        resultSet.close();
        statement.close();
        connection.close();

    }
}
