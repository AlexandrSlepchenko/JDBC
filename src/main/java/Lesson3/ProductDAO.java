package Lesson3;

import java.sql.*;
import java.util.ArrayList;

public class ProductDAO {
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.c6q51ov6uflr.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "Utarasta287";

    private static final String saveProduct = "INSERT INTO PRODUCT VALUES (?, ?, ?, ?)";
    private static final String updateProduct = "UPDATE PRODUCT SET NAME = ?, DESCRIPTION = ?,PRICE = ? WHERE ID=?";
    private static final String getAllProducts = "SELECT * FROM PRODUCT";
    private static final String deleteProduct = "DELETE FROM PRODUCT WHERE PRODUCT.ID = ?";


    public Product save(Product product) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement prepareStatement = connection.prepareStatement(saveProduct)) {

            prepareStatement.setLong(1, product.getId());
            prepareStatement.setString(2, product.getName());
            prepareStatement.setString(3, product.getDescription());
            prepareStatement.setInt(4, product.getPrice());

            int res = prepareStatement.executeUpdate();

            System.out.println("save was finished with result " + res);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }

        return product;
    }

    public Product update(Product product) {
        try (Connection connection = createConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(updateProduct)) {

            prepareStatement.setLong(1, product.getId());
            prepareStatement.setString(2, product.getName());
            prepareStatement.setString(3, product.getDescription());
            prepareStatement.setInt(4, product.getPrice());

            int res = prepareStatement.executeUpdate();

            System.out.println("save was finished with result " + res);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }

        return product;
    }

    public ArrayList<Product> getAllProducts() {
        try (Connection connection = createConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(getAllProducts);

            return mapper(resultSet);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }

        return null;
    }

    public void delete(long id) throws Exception {
        try (Connection connection = createConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteProduct);
            preparedStatement.setLong(1, id);
            int result = preparedStatement.executeUpdate();
            if (result == 0)
                throw new Exception("Product with id " + id + " was not found");

            System.out.println("Product was deleted");

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
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
