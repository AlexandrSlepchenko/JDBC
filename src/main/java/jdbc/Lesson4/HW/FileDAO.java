package jdbc.Lesson4.HW;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FileDAO {
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.c6q51ov6uflr.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "Utarasta287";

    private static StorageDAO storageDAO = new StorageDAO();

    private static final String saveFile = "INSERT INTO FILES VALUES (?, ?, ?, ?)";
    private static final String updateFile = "UPDATE FILES SET NAME = ?, DESCRIPTION = ?,PRICE = ? WHERE ID=?";
    private static final String getAllFiles = "SELECT * FROM FILES";
    private static final String deleteFile = "DELETE FROM FILES WHERE FILE.ID = ?";
    private static final String findFileById = "SELECT * FROM FILES WHERE FILE.ID = ?";
    private static final String findFilesByStorageId = "SELECT * FROM FILES WHERE FILES.STORAGE = ?";

    public File save(File file) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement prepareStatement = connection.prepareStatement(saveFile)) {

            prepareStatement.setLong(1, file.getId());
            prepareStatement.setString(2, file.getName());
            prepareStatement.setString(3, file.getFormat());
            prepareStatement.setLong(4, file.getSize());
            if (file.getStorageId() != null) {
                prepareStatement.setLong(5, file.getStorageId());
            }

            int res = prepareStatement.executeUpdate();

            System.out.println("save was finished with result " + res);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }

        return file;
    }

    public File update(File file) {
        try (Connection connection = createConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(updateFile)) {

            prepareStatement.setLong(1, file.getId());
            prepareStatement.setString(2, file.getName());
            prepareStatement.setString(3, file.getFormat());
            prepareStatement.setLong(4, file.getSize());
            if (file.getStorageId() != null) {
                prepareStatement.setLong(5, file.getStorageId());
            }

            int res = prepareStatement.executeUpdate();

            System.out.println("update was finished with result " + res);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }

        return file;
    }

    public void save(List<File> files) throws SQLException {
        try (Connection connection = createConnection()) {
            saveList(files, connection);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    private void saveList(List<File> files, Connection connection) throws SQLException {
        try (PreparedStatement prepareStatement = connection.prepareStatement(getAllFiles)) {

            connection.setAutoCommit(false);

            for (File file : files) {
                prepareStatement.setLong(1, file.getId());
                prepareStatement.setString(2, file.getName());
                prepareStatement.setString(3, file.getFormat());
                prepareStatement.setLong(4, file.getSize());
                if (file.getStorageId() != null) {
                    prepareStatement.setLong(5, file.getStorageId());
                } else {
                    prepareStatement.setLong(5, 0);
                }

                int res = prepareStatement.executeUpdate();

                System.out.println("save was finished with result " + res);
            }

            connection.commit();

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            connection.rollback();
            throw e;
        }
    }

    public void delete(long id) throws Exception {
        try (Connection connection = createConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteFile);
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

    public File findById(long id) {
        File file = null;
        try (Connection connection = createConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(findFileById);

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                file = new File(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getLong(4), resultSet.getLong(5));
            }

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return file;
    }

    public List<File> findFilesByStorageId(long id) throws Exception {
        try (Connection connection = createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findFilesByStorageId)) {

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<File> files = new ArrayList<>();
            while (resultSet.next()) {
                File file = new File(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getLong(4), resultSet.getLong(5));
                files.add(file);
            }

            return files;

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

}
