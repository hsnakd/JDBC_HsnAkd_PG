package jdbctests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class jdbcExamples {
    String dbURL = "jdbc:oracle:thin:@3.86.235.137:1521:xe";
    String dbUsername ="hr";
    String dbPassword ="hr";

    @Test
    public void test1() throws SQLException {

        Connection connection = DriverManager.getConnection(dbURL,dbUsername,dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM DEPARTMENTS");

        // move to first row
        resultSet.next();

        while (resultSet.next()){
            System.out.println(resultSet.getInt(1) + " - "
                            + resultSet.getString(2) + " - "
                            + resultSet.getInt(3) + " - "
                            + resultSet.getInt(4));
        }



//        while (resultSet.next()){
//            for (int i = 1; i < 5 ; i++) {
//                System.out.print(resultSet.getString(i));
//                System.out.print(" - ");
//            }
//            System.out.println("");
//        }

        resultSet = statement.executeQuery("SELECT * FROM REGIONS");

        while (resultSet.next()){
            System.out.println(resultSet.getInt(1) + " - "
                    + resultSet.getString(2));
        }


        // CLOSE CONNECTIONS
        resultSet.close();
        statement.close();
        connection.close();
    }

    @DisplayName("ResultSet Methods")
    @Test
    public void test2() throws SQLException {

        Connection connection = DriverManager.getConnection(dbURL,dbUsername,dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
/**
            ResultSet.TYPE_SCROLL_INSENSITIVE
                —> allow us to navigate up and down in query result.
            ResultSet.CONCUR_READ_ONLY
                —> read only, don’t update the results
 */
        ResultSet resultSet = statement.executeQuery("SELECT * FROM DEPARTMENTS");

/**
        ResultSet METHODS
            next()          -->     move to next row
            previous()      -->     move to previous row
            beforeFirst()   -->     goes before the first row
            afterLast()     -->     goes after last row
            getRow()        -->     get the current row number
            last()          -->     moves to last row
            absolute()      -->     goes specific row
   */


        // how to find how many rows we have for the query
        // move to last row
        resultSet.last();

        // get the row count
        int rowCount = resultSet.getRow();
        System.out.println(rowCount);


        // to move before first row after we use .last method
        resultSet.beforeFirst();

        // print all second column information
        while (resultSet.next()){
            System.out.println(resultSet.getString(2));
        }


//        // how to find how many rows we have for the query
//
//        int count = 0;
//        while (resultSet.next()){
//            count++;
//        }
//        System.out.println(count);



        // CLOSE CONNECTIONS
        resultSet.close();
        statement.close();
        connection.close();
    }

    @DisplayName("")
    @Test
    public void test3() throws SQLException {

        Connection connection = DriverManager.getConnection(dbURL,dbUsername,dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM DEPARTMENTS");


    // get the database relates data inside the databaseMetaData object
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        System.out.println("databaseMetaData.getUserName() = " + databaseMetaData.getUserName());
        System.out.println("databaseMetaData.getDatabaseProductName() = " + databaseMetaData.getDatabaseProductName());
        System.out.println("databaseMetaData.getDatabaseProductVersion() = " + databaseMetaData.getDatabaseProductVersion());
        System.out.println("databaseMetaData.getDriverName() = " + databaseMetaData.getDriverName());
        System.out.println("databaseMetaData.getDriverVersion() = " + databaseMetaData.getDriverVersion());

    // get the resultSetMetaData --> rsmd

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

    // how many columns we have
        int colCount = resultSetMetaData.getColumnCount();
        System.out.println(colCount);

        // getting column names
        System.out.println("resultSetMetaData.getColumnName(1) = " + resultSetMetaData.getColumnName(1));
        System.out.println("resultSetMetaData.getColumnName(1) = " + resultSetMetaData.getColumnName(2));

        // getting column names.getColumnName(i) --> gets column name
        // getting column names.getColumnCount(i) --> total number of column
        // print all the column names dynamically         for (int i = 1; i <= colCount; i++) {
        for (int i = 1; i <= colCount; i++) {
            System.out.println(resultSetMetaData.getColumnName(i));
        }




        // CLOSE CONNECTIONS
        resultSet.close();
        statement.close();
        connection.close();
    }

}
