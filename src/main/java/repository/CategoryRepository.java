package repository;
import models.Category;
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
        return preparedStatement.executeUpdate();
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
            return new Category(id,categoryName,categoryDescription);
        } else
            return null;
    }
    public Category loadById(int id) throws SQLException {

        String loadCategory = "SELECT * FROM category WHERE category_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(loadCategory);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int idC = resultSet.getInt("category_id");
            String categoryName = resultSet.getString("category_name");
            String categoryDescription = resultSet.getString("category_description");
            return new Category(idC,categoryName,categoryDescription);
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

        String loadAllCategories = "SELECT * FROM category ORDER BY category_id";
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
            int Cid = resultSet.getInt("category_id");
            String categoryName = resultSet.getString("category_name");
            String categoryDescription = resultSet.getString("category_description");
            return new Category(Cid,categoryName,categoryDescription);
        }
        else
            return null;
    }
    public int deleteCategory(int id) throws SQLException {

        String deleteCategory = "DELETE FROM category WHERE category_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteCategory);
        preparedStatement.setInt(1,id);
        return preparedStatement.executeUpdate();
    }
    public int editCategoryName(int id,String newCategoryName) throws SQLException {

        String editCategoryName = "UPDATE category SET category_name = ? WHERE category_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(editCategoryName);
        preparedStatement.setString(1,newCategoryName);
        preparedStatement.setInt(2,id);
        return preparedStatement.executeUpdate();
    }
    public int editCategoryDescription(int id,String newCategoryDescription) throws SQLException {

        String editCategoryDescription = "UPDATE category SET category_description = ? WHERE category_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(editCategoryDescription);
        preparedStatement.setString(1,newCategoryDescription);
        preparedStatement.setInt(2,id);
        return preparedStatement.executeUpdate();
    }
}
