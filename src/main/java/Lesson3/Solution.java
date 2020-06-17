package Lesson3;

import java.sql.*;
import java.util.ArrayList;

public class Solution {
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.c6q51ov6uflr.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "Utarasta287";

    private static final String getAllProductsByPrice = "SELECT * FROM PRODUCT WHERE PRICE BETWEEN (? AND ?)";
    private static final String findProductsByName = "SELECT * FROM PRODUCT WHERE NAME LIKE ?";
    private static final String findProductsByDescription = "SELECT * FROM PRODUCT WHERE DESCRIPTION IS NULL";

    public ArrayList<Product> findProductsByPrice(int price, int delta) {
        try (Connection connection = createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getAllProductsByPrice)) {
            preparedStatement.setInt(1, price - delta);
            preparedStatement.setInt(2, price + delta);

            ResultSet resultSet = preparedStatement.executeQuery();

            return mapper(resultSet);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<Product> findProductsByName(String word) {
        try (Connection connection = createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findProductsByName)) {
            preparedStatement.setString(1, word);

            ResultSet resultSet = preparedStatement.executeQuery();

            return mapper(resultSet);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<Product> findProductsByDescription() {
        try (Connection connection = createConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(findProductsByDescription);

            return mapper(resultSet);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }

        return null;
    }


    private static ArrayList<Product> mapper(ResultSet resultSet) throws SQLException {
        ArrayList<Product> products = new ArrayList<>();

        while (resultSet.next()) {
            long id = resultSet.getLong(1);
            String name = resultSet.getString(2);
            String description = resultSet.getString(3);
            int price = resultSet.getInt(4);

            products.add(new Product(id, name, description, price));
        }

        return products;
    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
