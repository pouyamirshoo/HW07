package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Users;


public class UserRepository {
    private final Connection connection;

    public UserRepository(Connection connection) {
        this.connection = connection;
    }

    public int save(Users user) throws SQLException {

        String SaveUser = "INSERT INTO users(user_first_name,user_username,email,user_password) VALUES(?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(SaveUser);
        preparedStatement.setString(1, user.getUserFullName());
        preparedStatement.setString(2, user.getUserName());
        preparedStatement.setString(3, user.getUserEmail());
        preparedStatement.setString(4, user.getUserPassword());
        int result = preparedStatement.executeUpdate();
        return result;
    }

    public Users load(String username) throws SQLException {

        String loadUser = "SELECT * FROM users WHERE user_username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(loadUser);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            int id = resultSet.getInt("user_id");
            String firstName = resultSet.getString("user_first_name");
            String fetchUsername = resultSet.getString("user_username");
            String email = resultSet.getString("email");
            String password = resultSet.getString("user_password");
            Users user = new Users(id,firstName,fetchUsername,email,password);
            return user;
        }
        else
            return null;
    }
}
