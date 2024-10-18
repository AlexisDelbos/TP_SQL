package fr.fms.dao;

import fr.fms.entities.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao implements Dao<Users> {

    private Connection connection;

    public UserDao() {
        this.connection = BddConnection.getConnection();
    }

    public void create(Users user) {
        String insertUserSQL = "INSERT INTO T_Users (Login, Password) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertUserSQL)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Users read(int idUser) {
        String selectUserSQL = "SELECT * FROM T_Users WHERE IdUser = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectUserSQL)) {
            preparedStatement.setInt(1, idUser);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String login = resultSet.getString("Login");
                    String password = resultSet.getString("Password");
                    return new Users(idUser, login, password);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    public void update(String columnName, Object newValue, int idUser) {
        String updateUserSQL = "UPDATE T_Users SET " + columnName + " = ? WHERE IdUser = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateUserSQL)) {
            preparedStatement.setObject(1, newValue);
            preparedStatement.setInt(2, idUser);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public void delete(int idUser) {
        String deleteUserSQL = "DELETE FROM T_Users WHERE IdUser = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteUserSQL)) {
            preparedStatement.setInt(1, idUser);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean authenticate(String login, String password) {
        String authenticateSQL = "SELECT * FROM T_Users WHERE Login = ? AND Password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(authenticateSQL)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
