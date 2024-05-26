package utilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtils {
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    // Database connection details
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "mysecretpassword";
    private static final String SCHEMA_NAME = "information_schema";

    // Method to create a database connection
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
}
