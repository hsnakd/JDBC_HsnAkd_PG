package jdbctests;

import java.sql.*;

public class NavigatingResultSet {
    public static void main(String[] args) throws SQLException {
        String dbURL = "jdbc:oracle:thin:@3.86.235.137:1521:xe";
        String dbUsername ="hr";
        String dbPassword ="hr";


        Connection connection = DriverManager.getConnection(dbURL,dbUsername,dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM REGIONS");

        resultSet.next();
        System.out.println("next() : " + resultSet.getString(1) + " - " + resultSet.getString(2));
        resultSet.first();
        System.out.println("first() : " + resultSet.getString(1) + " - " + resultSet.getString(2));
        resultSet.last();
        System.out.println("last() : " + resultSet.getString(1) + " - " + resultSet.getString(2));
        resultSet.previous();
        System.out.println("previous() : " + resultSet.getString(1) + " - " + resultSet.getString(2));
        resultSet.first();
        System.out.println("first() : " + resultSet.getString(1) + " - " + resultSet.getString(2));
        resultSet.absolute(2);
        System.out.println("absolute(2) : " + resultSet.getString(1) + " - " + resultSet.getString(2));

        resultSet.beforeFirst();
        resultSet.next();
        System.out.println("beforeFirst() : " + resultSet.getString(1) + " - " + resultSet.getString(2));

        resultSet.afterLast();
        resultSet.previous();
        System.out.println("afterLast() : " + resultSet.getString(1) + " - " + resultSet.getString(2));

        resultSet.last();
        System.out.println("Row count is : " + resultSet.getRow());

        // CLOSE CONNECTIONS
        resultSet.close();
        statement.close();
        connection.close();
    }
}

/*
 * next()        ==> move to next row and return true/false according to if we have valid row
 * previous()    ==> move to previous row and return true/false according to if we have valid row
 * first()       ==> move to first row from anywhere
 * last()        ==> move to last row from anywhere
 * beforeFirst() ==> move to before first location from anywhere
 * afterLast()   ==> move to after last location from anywhere
 * absolute(8)   ==> move to any row by using row number, for example 8 in this case

 * getString(ColumnName)     ==>
 * getString(ColumnIndex)    ==>
 * getInt(ColumnName)        ==>
 * getInt(ColumnIndex)       ==>
 * getDouble(ColumnName)     ==>
 * getDouble(ColumnIndex)    ==>
 * getDate(ColumnName)       ==>
 * getDate(ColumnIndex)      ==>

 */