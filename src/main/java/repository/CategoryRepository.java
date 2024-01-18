package repository;

;

import feilds.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryRepository {

    private final Connection connection;
    public CategoryRepository(Connection connection) {
        this.connection = connection;
    }

    public int save(Category category) throws SQLException {

        String saveCategory = "INSERT INTO category(category_name,category_description) VALUES(?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(saveCategory);
        preparedStatement.setString(1, category.getCategoryName());
        preparedStatement.setString(2, category.getCategoryDescription());
        int result = preparedStatement.executeUpdate();
        return result;
    }

}
