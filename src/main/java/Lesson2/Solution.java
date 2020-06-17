package Lesson2;

import java.sql.*;
import java.util.ArrayList;


public class Solution {
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.c6q51ov6uflr.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "Utarasta287";

    private static final String getAllProducts = "SELECT * FROM PRODUCT";
    private static final String getProductsByPrice = "SELECT * FROM PRODUCT WHERE PRICE <= 100";
    private static final String getProductsByDescription = "SELECT * FROM PRODUCT WHERE LENGTH(DESCRIPTION) > ?";
    private static final String increasePrice = "UPDATE PRODUCT SET PRICE = PRICE + 100 WHERE PRICE < 970";
    private static final String updateProductsDescription = "UPDATE PRODUCT SET DESCRIPTION = ? WHERE ID = ?";

    private static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public static ArrayList<Product> getAllProducts() {
        try (Connection connection = createConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(getAllProducts);

            return mapper(resultSet);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public static ArrayList<Product> getProductsByPrice() {
        try (Connection connection = createConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(getProductsByPrice);

            return mapper(resultSet);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public static ArrayList<Product> getProductsByDescription() {
        try (Connection connection = createConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(getProductsByDescription);
            preparedStatement.setString(1, "50");
            ResultSet resultSet = preparedStatement.executeQuery();

            return mapper(resultSet);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public static void increasePrice() {
        try (Connection connection = createConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(increasePrice);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    public static void changeDescription() {
        try (Connection connection = createConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(getProductsByDescription);
            preparedStatement.setString(1, "100");
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<Product> allProducts = mapper(resultSet);
            for (Product product : allProducts) {
                PreparedStatement statement = connection.prepareStatement(updateProductsDescription);
                statement.setString(1, deleteLastSentence(product.getDescription()));
                statement.setLong(2, product.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    private static String deleteLastSentence(String text) {
        int lastIndexOfPoint = text.lastIndexOf('.', text.length() - 2);
        int lastIndexOfQuery = text.lastIndexOf('?', text.length() - 2);
        int lastIndexOfExclamation = text.lastIndexOf('!', text.length() - 2);

        int lastSentenceStartIndex = lastIndexOfPoint > lastIndexOfQuery ? lastIndexOfPoint : lastIndexOfQuery;
        lastSentenceStartIndex = lastSentenceStartIndex > lastIndexOfExclamation ? lastSentenceStartIndex : lastIndexOfExclamation;

        return lastSentenceStartIndex == -1 ? "" : text.substring(0, lastSentenceStartIndex + 1);
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
}
