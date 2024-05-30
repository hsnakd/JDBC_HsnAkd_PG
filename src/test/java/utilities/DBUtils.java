package utilities;

import java.sql.*;
import java.util.*;

public class DBUtils {
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;
    private static ResultSetMetaData rsmd ;


    // Database connection details
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "mysecretpassword";
    private static final String SCHEMA_NAME = "information_schema";

    // Method to create a database connection
    /**
     * Create connection method , just checking one connection successful or not
     */
    public static Connection createConnection() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    // Method to create a database connection
    public static Connection createConnection(String DB_URL, String DB_USERNAME, String DB_PASSWORD) {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }



    // Method to create a database connection with schema
    public static Connection createConnectionWithSchema() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            connection.setSchema(SCHEMA_NAME);
            System.out.println("CONNECTION SUCCESSFUL");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("CONNECTION HAS FAILED " + e.getMessage() );
        }
        return connection;
    }

    // Method to create a database connection with schema
    public static Connection createConnectionWithSchema(String DB_URL, String DB_USERNAME, String DB_PASSWORD, String SCHEMA_NAME) {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            connection.setSchema(SCHEMA_NAME);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


    // Method to get the current connection
    public static Connection getConnection() {
        return connection;
    }

    // Method to close a database connection
    public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to close a statement
    public static void closeStatement(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to close a result set
    public static void closeResultSet(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to destroy resources
    public static void destroy() {
        closeResultSet(resultSet);
        closeStatement(statement);
        closeConnection(connection);
    }

    // Method to execute a query and return a list of maps
    public static List<Map<String, Object>> executeQueryAndGetResult(String query) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(metaData.getColumnName(i), resultSet.getObject(i));
                }
                resultList.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    // Method to execute a query and return a list of objects
    public static List<Object> getQueryResultList(String query) {
        List<Object> resultList = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                resultList.add(resultSet.getObject(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    // Method to execute a query and return a single row as a map
    public static Map<String, Object> getRowMap(String query) {
        Map<String, Object> rowMap = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            if (resultSet.next()) {
                rowMap = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object columnValue = resultSet.getObject(i);
                    rowMap.put(columnName, columnValue);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowMap;
    }

    // Method to execute an update query and return the number of affected rows
    public static int executeUpdate(String query) {
        int rowsAffected = 0;
        try {
            statement = connection.createStatement();
            rowsAffected = statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }
    public static List<Map<String, Object>> getQueryResultMap(String query) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                Map<String, Object> rowMap = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object columnValue = resultSet.getObject(i);
                    rowMap.put(columnName, columnValue);
                }
                resultList.add(rowMap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }


    /**
     * Run the sql query provided and return ResultSet object
     * @param sql the query to run
     * @return ResultSet object  that contains data
     */
    public static ResultSet runQuery(String sql){

        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(sql); // setting the value of ResultSet object
            rsmd = resultSet.getMetaData() ;  // setting the value of ResultSetMetaData for reuse
        }catch(Exception e){
            System.out.println("ERROR OCCURRED WHILE RUNNING QUERY "+ e.getMessage() );
        }

        return resultSet ;

    }

    /**
     * This method will reset the cursor to before first location
     */
    private static void resetCursor(){

        try {
            resultSet.beforeFirst();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    // find out the row count

    /**
     * find out the row count
     * @return row count of this ResultSet
     */
    public static int getRowCount(){

        int rowCount = 0 ;
        try {
            resultSet.last() ;
            rowCount = resultSet.getRow() ;
        } catch (Exception e) {
            System.out.println("ERROR OCCURRED WHILE GETTING ROW COUNT " + e.getMessage() );
        }finally {
            resetCursor();
        }

        return rowCount ;

    }


    /**
     * find out the column count
     * @return column count of this ResultSet
     */
    public static int getColumnCount(){

        int columnCount = 0 ;

        try {
            columnCount = rsmd.getColumnCount();

        } catch (Exception e) {
            System.out.println("ERROR OCCURRED WHILE GETTING COLUMN COUNT " + e.getMessage() );
        }

        return columnCount ;

    }



    /**
     * // Get all the Column names into a list object
     * @return  List<String>
     */
    public static List<String> getAllColumnNamesAsList(){

        List<String> columnNameLst = new ArrayList<>();

        try {
            for (int colIndex = 1; colIndex <= getColumnCount() ; colIndex++) {
                String columnName =  rsmd.getColumnName(colIndex) ;
                columnNameLst.add(columnName) ;
            }
        }catch (Exception e){
            System.out.println("ERROR OCCURRED WHILE getAllColumnNamesAsList "+ e.getMessage());
        }

        return columnNameLst ;

    }

    // get entire row of data according to row number

    /**
     * Getting entire row of data in a List of String
     * @param rowNum row number to get as a list
     * @return row data as List of String
     */
    public static List<String> getRowDataAsList( int rowNum ){

        List<String> rowDataAsLst = new ArrayList<>();
        int colCount =  getColumnCount() ;

        try {
            resultSet.absolute( rowNum );

            for (int colIndex = 1; colIndex <= colCount ; colIndex++) {

                String cellValue =  resultSet.getString( colIndex ) ;
                rowDataAsLst.add(   cellValue  ) ;

            }


        } catch (Exception e) {
            System.out.println("ERROR OCCURRED WHILE getRowDataAsList " + e.getMessage() );
        }finally {
            resetCursor();
        }


        return rowDataAsLst ;
    }



    /**
     * getting the cell value according to row num and column index
     * @param rowNum  row number to get the data from
     * @param columnIndex column number to get the data from
     * @return the value in String at that location
     */
    public static String getCellValue(int rowNum , int columnIndex) {

        String cellValue = "" ;

        try {
            resultSet.absolute(rowNum) ;
            cellValue = resultSet.getString(columnIndex ) ;

        } catch (Exception e) {
            System.out.println("ERROR OCCURRED WHILE getCellValue " + e.getMessage() );
        }finally {
            resetCursor();
        }
        return cellValue ;

    }

    /**
     * getting the cell value according to row num and column name
     * @param rowNum  row number to get the data from
     * @param columnName column Name to get the data from
     * @return the value in String at that location
     */
    public static String getCellValue(int rowNum ,String columnName){

        String cellValue = "" ;

        try {
            resultSet.absolute(rowNum) ;
            cellValue = resultSet.getString( columnName ) ;

        } catch (Exception e) {
            System.out.println("ERROR OCCURRED WHILE getCellValue " + e.getMessage() );
        }finally {
            resetCursor();
        }
        return cellValue ;

    }


    /**
     * Get First Cell Value at First row First Column
     */
    public static String getFirstRowFirstColumn(){

        return getCellValue(1,1) ;

    }

    //

    /**
     * getting entire column data as list according to column number
     * @param columnNum column number to get all data
     * @return List object that contains all rows of that column
     */
    public static List<String> getColumnDataAsList(int columnNum){

        List<String> columnDataLst = new ArrayList<>();

        try {
            resultSet.beforeFirst(); // make sure the cursor is at the first location
            while( resultSet.next() ){

                String cellValue = resultSet.getString(columnNum) ;
                columnDataLst.add(cellValue) ;
            }

        } catch (Exception e) {
            System.out.println("ERROR OCCURRED WHILE getColumnDataAsList " + e.getMessage() );
        }finally {
            resetCursor();
        }


        return columnDataLst ;

    }

    /**
     * getting entire column data as list according to column Name
     * @param columnName column name to get all data
     * @return List object that contains all rows of that column
     */
    public static List<String> getColumnDataAsList(String columnName){

        List<String> columnDataLst = new ArrayList<>();

        try {
            resultSet.beforeFirst(); // make sure the cursor is at before first location
            while( resultSet.next() ){

                String cellValue = resultSet.getString(columnName) ;
                columnDataLst.add(cellValue) ;
            }

        } catch (Exception e) {
            System.out.println("ERROR OCCURRED WHILE getColumnDataAsList " + e.getMessage() );
        }finally {
            resetCursor();
        }


        return columnDataLst ;

    }


    /**
     * display all data from the ResultSet Object
     */
    public static void  displayAllData(){

        int columnCount = getColumnCount() ;
        resetCursor();
        try{

            while(resultSet.next()){

                for (int colIndex = 1; colIndex <= columnCount; colIndex++) {

                    //System.out.print( resultSet.getString(colIndex) + "\t" );
                    System.out.printf("%-25s", resultSet.getString(colIndex));
                }
                System.out.println();

            }

        }catch(Exception e){
            System.out.println("ERROR OCCURRED WHILE displayAllData " + e.getMessage() );
        }finally {
            resetCursor();
        }

    }

    /**
     * Save entire row data as Map<String,String>
     * @param rowNum row number
     * @return Map object that contains key value pair
     *      key     : column name
     *      value   : cell value
     */
    public static Map<String,String> getRowMap(int rowNum){

        Map<String,String> rowMap = new LinkedHashMap<>();
        int columnCount = getColumnCount() ;

        try{

            resultSet.absolute(rowNum) ;

            for (int colIndex = 1; colIndex <= columnCount ; colIndex++) {
                String columnName = rsmd.getColumnName(colIndex) ;
                String cellValue  = resultSet.getString(colIndex) ;
                rowMap.put(columnName, cellValue) ;
            }

        }catch(Exception e){
            System.out.println("ERROR OCCURRED WHILE getRowMap " + e.getMessage() );
        }finally {
            resetCursor();
        }


        return rowMap ;
    }
    /**
     * We know how to store one row as map object
     * Now Store All rows as List of a Map object
     * @return List of a Map object that contain each row data as Map<String,String>
     */
    public static List<Map<String,String>> getAllRowAsListOfMap(){

        List<Map<String,String>> allRowLstOfMap = new ArrayList<>();
        int rowCount = getRowCount() ;

        // move from first row till last row
        // get each row as a map object and add it to the list

        for (int rowIndex = 1; rowIndex <= rowCount ; rowIndex++) {

            Map<String,String> rowMap = getRowMap(rowIndex);
            allRowLstOfMap.add( rowMap ) ;

        }
        resetCursor();

        return allRowLstOfMap ;

    }
}
