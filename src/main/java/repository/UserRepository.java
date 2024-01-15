package repository;

import Connection.JdbcConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import feilds.Users;


public class UserRepository {
    private final Connection connection;

    public UserRepository(Connection connection){
        this.connection = connection;
    }

    public int save(Users user) throws SQLException {

        String SaveUser = "INSERT INTO users(user_first_name,user_username,email,user_password) VALUES(?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(SaveUser);
        preparedStatement.setString(1,user.getUserFullName());
        preparedStatement.setString(2,user.getUserName());
        preparedStatement.setString(3,user.getUserEmail());
        preparedStatement.setString(4,user.getUserPassword());
        int result =  preparedStatement.executeUpdate();
        return result;
    }
}
