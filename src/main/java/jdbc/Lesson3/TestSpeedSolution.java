package jdbc.Lesson3;

import java.sql.*;
import java.util.ArrayList;

public class TestSpeedSolution {

    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.c6q51ov6uflr.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "Utarasta287";

    private static final String saveTestSpeedsRequest = "INSERT INTO TEST_SPEED VALUES (?, ?, ?)";
    private static final String testDeleteByIdPerformanceRequest = "DELETE FROM TEST_SPEED WHERE TEST_SPEED.ID = ?";
    private static final String testDeletePerformanceRequest = "DELETE FROM TEST_SPEED";
    private static final String testSelectByIdPerformanceRequest = "SELECT * FROM TEST_SPEED WHERE TEST_SPEED.ID = ?";
    private static final String testSelectPerformanceRequest = "SELECT * FROM TEST_SPEED";

    public long testSavePerformance() {
        try (Connection connection = getConnection()) {

            ArrayList<TestSpeed> testSpeeds = new ArrayList<>();
            for (int i = 1; i <= 1000; i++) {
                testSpeeds.add(new TestSpeed(i, String.valueOf(i), i));
            }

            PreparedStatement preparedStatement = connection.prepareStatement(saveTestSpeedsRequest);

            long start = System.currentTimeMillis();
            for (TestSpeed testSpeed : testSpeeds) {
                preparedStatement.setLong(1, testSpeed.getId());
                preparedStatement.setString(2, testSpeed.getSomeString());
                preparedStatement.setInt(3, testSpeed.getSomeNumber());

                preparedStatement.executeUpdate();
            }

            long finish = System.currentTimeMillis();
            return finish - start;

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }

        return -1;
    }

    public long testDeleteByIdPerformance() {
        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(testDeleteByIdPerformanceRequest);

            long start = System.currentTimeMillis();

            for (int i = 1; i <= 1000; i++) {
                preparedStatement.setLong(1, i);

                preparedStatement.executeUpdate();
            }

            long finish = System.currentTimeMillis();
            return finish - start;

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }

        return -1;
    }

    public long testDeletePerformance() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            long start = System.currentTimeMillis();

            statement.executeUpdate(testDeletePerformanceRequest);

            long finish = System.currentTimeMillis();
            return finish - start;

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }

        return -1;
    }

    public long testSelectByIdPerformance() {
        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(testSelectByIdPerformanceRequest);

            long start = System.currentTimeMillis();

            for (int i = 1; i <= 1000; i++) {
                preparedStatement.setLong(1, i);

                preparedStatement.executeUpdate();
            }

            long finish = System.currentTimeMillis();
            return finish - start;

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }

        return -1;
    }

    public long testSelectPerformance() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            long start = System.currentTimeMillis();

            statement.executeUpdate(testSelectPerformanceRequest);

            long finish = System.currentTimeMillis();
            return finish - start;

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }

        return -1;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
