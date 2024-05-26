package jdbctests;

import org.junit.jupiter.api.Test;
import utilities.DBUtils;

import java.util.List;
import java.util.Map;

public class DButilPractice_WithSchema {

    @Test
    public void test0(){

        //create connection to db that you put information inside the method
//        DBUtils2.createConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "mysecretpassword");
        DBUtils.createConnectionWithSchema();

        int rownum = 6;
        String query =  "SELECT * \n" +
                "from employees\n" ;   // PostgreSQL
        // oracle    "where rownum <" + rownum;

        // DBUtils.getQueryResultMap(query)
        // —> returns list of maps, useful when you are getting multiple rows of a result.
        List<Map<String, Object>> queryData = DBUtils.getQueryResultMap(query);

        //print the result
        for (Map<String, Object> row : queryData) {
            System.out.println(row.toString());
        }

        //close the connection
//        DBUtils.destroy();
    }


    @Test
    public void test1(){

        //create connection to db that you put information inside the method
//        DBUtils2.createConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "mysecretpassword");
        DBUtils.createConnectionWithSchema();

        int rownum = 6;
        String query =  "SELECT first_name,last_name,salary,job_id\n" +
                        "from employees\n" +
                        "LIMIT " + rownum;   // PostgreSQL
        // oracle    "where rownum <" + rownum;

        // DBUtils.getQueryResultMap(query)
        // —> returns list of maps, useful when you are getting multiple rows of a result.
        List<Map<String, Object>> queryData = DBUtils.getQueryResultMap(query);

        //print the result
        for (Map<String, Object> row : queryData) {
            System.out.println(row.toString());
        }

        //close the connection
//        DBUtils.destroy();
    }


    @Test
    public void test2(){
        //create connection
//        DBUtils2.createConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "mysecretpassword");
        DBUtils.createConnectionWithSchema();
        String query =  "SELECT first_name,last_name,salary,job_id\n" +
                        "from employees\n" +
                        "LIMIT 6"; // PostgreSQL
          // oracle      "where rownum <2";


        // DBUtil.getRowMap(query)
        // —> returns maps of string of object useful when we have only one result.
        Map<String, Object> rowMap = DBUtils.getRowMap(query);

        System.out.println(rowMap.toString());

        //close the connection
//        DBUtils.destroy();

    }





}
