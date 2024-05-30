package jdbcTests.fastTrack_day02;

import utilities.DBUtils;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class P05_DBUtilPractice {

    @Test
    public  void dbUtilPractice() {
        // Create conn
        DBUtils.createConnectionWithSchema();


        // Run Query
        DBUtils.runQuery("select first_name,last_name,salary from employees");

        System.out.println("-----------------GET ME FIRST ROW FIRST COLUMN --> STEVEN -----------------");
        System.out.println(DBUtils.getFirstRowFirstColumn());

        System.out.println("-----------------GET ME ALL COLUMN NAMES-----------------");
        System.out.println(DBUtils.getAllColumnNamesAsList());

        System.out.println("-----------------GET ME ALL FIRST NAMES-----------------");
        System.out.println(DBUtils.getColumnDataAsList(1));


        System.out.println("-----------------GET ME HOW MANY ROW WE HAVE -----------------");
        System.out.println(DBUtils.getRowCount());

        System.out.println("-----------------GET ME ALL DATA FROM EXECUTION-----------------");

        List<Map<String, String>> allRowAsListOfMap = DBUtils.getAllRowAsListOfMap();

        for (Map<String, String> eachMap : allRowAsListOfMap) {
            System.out.println(eachMap);
        }

        //Close Connection
        DBUtils.destroy();


    }
}
