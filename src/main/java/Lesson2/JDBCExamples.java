package Lesson2;

import java.sql.*;
import java.util.Date;

public class JDBCExamples {

    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.c6q51ov6uflr.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "Utarasta287";

    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {
//            Boolean res = statement.execute("INSERT INTO PRODUCTS VALUES (2, 'toy222', 'for children', 77)");
//            System.out.println(res);

//            Boolean res = statement.execute("DELETE FROM PRODUCTS WHERE NAME = 'toy222'");
//            System.out.println(res);

//            int response = statement.executeUpdate("INSERT INTO PRODUCTS VALUES (5, 'car', 'for children', 880)");
//            System.out.println(response);

            int response = statement.executeUpdate("DELETE FROM PRODUCTS WHERE NAME = 'toy222'");
            System.out.println(response);


        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }
}
