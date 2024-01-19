package repository;

;

import feilds.Brands;
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
    public int numOfLoadAll() throws SQLException {

        int num = 0;
        String numOfLoadAllCategories = "SELECT * FROM category ";
        PreparedStatement preparedStatement = connection.prepareStatement(numOfLoadAllCategories);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            num++;
        }
        return num;
    }

    public Category[] loadAll() throws SQLException {

        Category [] categories = new Category[numOfLoadAll()];
        int i = 0;

        String loadAllCategories = "SELECT * FROM category ";
        PreparedStatement preparedStatement = connection.prepareStatement(loadAllCategories);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("category_id");
            String categoryName1 = resultSet.getString("category_name");
            String categoryDescription = resultSet.getString("category_description");
            Category category = new Category(id, categoryName1, categoryDescription);
            categories [i] = category;
            i++;
        }
        return categories;
    }
    public Category takeCategory(int id) throws SQLException {

        String takeCategory = "SELECT * FROM category WHERE category_id =?";
        PreparedStatement preparedStatement = connection.prepareStatement(takeCategory);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            String categoryName = resultSet.getString("category_name");
            String categoryDescription = resultSet.getString("category_description");
            Category category = new Category(categoryName,categoryDescription);
            return category;
        } else
            return null;
    }
}
