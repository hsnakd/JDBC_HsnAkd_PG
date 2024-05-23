package utilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtils2 {
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static Connection createConnection(String dbUrl, String dbUsername, String dbPassword) {
        try {
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static Connection createConnection(String dbUrl, String dbUsername, String dbPassword, String schema) {
        try {
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            connection.setSchema(schema);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeStatement(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeResultSet(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public static void destroy() {
        closeResultSet(resultSet);
        closeStatement(statement);
        closeConnection(connection);
    }
}
