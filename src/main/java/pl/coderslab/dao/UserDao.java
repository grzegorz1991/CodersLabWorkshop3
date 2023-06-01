package pl.coderslab.dao;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.entity.User;
import pl.coderslab.utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public void addUser(String email, String username, String password) {

        String hashedPassword = hashPassword(password);

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO users (email, username, password) VALUES (?, ?, ?)")) {
            statement.setString(1, email);
            statement.setString(2, username);
            statement.setString(3, hashedPassword);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUser(User user) {

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO users (email, username, password) VALUES (?, ?, ?)")) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getHashedPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(int id, String email, String username) {
        try (Connection connection = DbUtil.getConnection();
             Statement statement = connection.createStatement()) {
            String query = String.format("UPDATE users SET email = '%s', username = '%s' WHERE id = %d",
                    email, username, id);
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User findUserById(int id) {
        try (Connection connection = DbUtil.getConnection();
             Statement statement = connection.createStatement()) {
            String query = String.format("SELECT * FROM users WHERE id = %d", id);
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                int userId = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                return new User(userId, email, username, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteUserById(int id) {
        try (Connection connection = DbUtil.getConnection();
             Statement statement = connection.createStatement()) {
            String query = String.format("DELETE FROM users WHERE id = %d", id);
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             Statement statement = connection.createStatement()) {
            String query = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                users.add(new User(id, email, username, password));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public String hashPassword(String password) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        return hashedPassword;
    }

}