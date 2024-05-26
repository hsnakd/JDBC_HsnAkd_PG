package jdbctests;

import java.sql.*;

public class NavigatingResultSet {
    public static void main(String[] args) throws SQLException {
        // Database connection details
        String dbURL = "jdbc:postgresql://localhost:5432/postgres";
        String dbUsername = "postgres";
        String dbPassword = "mysecretpassword";
        String schemaName = "information_schema";  // Typically, user tables are in the public schema

        // Establish the connection
        Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        connection.setSchema(schemaName);
        // Create a Statement with scrollable and read-only ResultSet
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        // Execute the query
        ResultSet resultSet = statement.executeQuery("SELECT * FROM regions");

        // Navigate the ResultSet
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

        // Close connections
        resultSet.close();
        statement.close();
        connection.close();
    }
}

/*
 * next()        ==> Move to the next row and return true/false based on whether a valid row exists
 * previous()    ==> Move to the previous row and return true/false based on whether a valid row exists
 * first()       ==> Move to the first row from anywhere
 * last()        ==> Move to the last row from anywhere
 * beforeFirst() ==> Move to the before first location from anywhere
 * afterLast()   ==> Move to the after last location from anywhere
 * absolute(8)   ==> Move to any row using the row number, for example, 8 in this case

 * getString(ColumnName)     ==> Retrieve the value of the specified column as a String using column name
 * getString(ColumnIndex)    ==> Retrieve the value of the specified column as a String using column index
 * getInt(ColumnName)        ==> Retrieve the value of the specified column as an int using column name
 * getInt(ColumnIndex)       ==> Retrieve the value of the specified column as an int using column index
 * getDouble(ColumnName)     ==> Retrieve the value of the specified column as a double using column name
 * getDouble(ColumnIndex)    ==> Retrieve the value of the specified column as a double using column index
 * getDate(ColumnName)       ==> Retrieve the value of the specified column as a Date using column name
 * getDate(ColumnIndex)      ==> Retrieve the value of the specified column as a Date using column index
 */
