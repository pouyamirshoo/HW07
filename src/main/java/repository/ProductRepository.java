package repository;

import feilds.Brands;
import feilds.Category;
import feilds.Products;
import service.BrandService;
import service.CategoryService;
import utility.ApplicationContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRepository {
    private final Connection connection;
    private final CategoryService categoryService = ApplicationContext.getCategoryService();
    private final BrandService brandService = ApplicationContext.getBrandService();


    public ProductRepository(Connection connection) {
        this.connection = connection;
    }

    public int save(Products product) throws SQLException {

        java.util.Date myDate = new java.util.Date(product.getProductCreatDate());
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());

        String saveProduct = "INSERT INTO product(product_name,product_creat_date,category_id_fk,brand_id_fk) VALUES(?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(saveProduct);
        preparedStatement.setString(1,product.getProductName());
        preparedStatement.setDate(2,sqlDate);
        preparedStatement.setInt(3,product.categoryId());
        preparedStatement.setInt(4,product.brandId());
        int result = preparedStatement.executeUpdate();
        return result;
    }
    public Products load(String productName) throws SQLException {

        String load = "SELECT * FROM product WHERE product_name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(load);
        preparedStatement.setString(1,productName);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            int id = resultSet.getInt("product_id");
            String productName1 = resultSet.getString("product_name");
            String product_creat_date = resultSet.getString("product_creat_date");
            int category_id_fk = resultSet.getInt("category_id_fk");
            Category category = categoryService.loadCategoryByID(category_id_fk);
            int brand_id_fk = resultSet.getInt("brand_id_fk");
            Brands brand = brandService.loadBrandById(brand_id_fk);

            Products product = new Products(id,productName1,product_creat_date,category,brand);
            return product;
        }
        else
            return null;
    }
}
