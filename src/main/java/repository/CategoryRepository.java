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
    public Category load(String categoryName) throws SQLException {

        String loadCategory = "SELECT * FROM category WHERE category_name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(loadCategory);
        preparedStatement.setString(1, categoryName);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int id = resultSet.getInt("category_id");
            String categoryName1 = resultSet.getString("category_name");
            String categoryDescription = resultSet.getString("category_description");
            Category category = new Category(id,categoryName,categoryDescription);
            return category;
        } else
            return null;
    }
}
