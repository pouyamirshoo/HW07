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
    public int numOfAllProducts() throws SQLException {

        int numOfAllProducts = 0;
        String SQL = "SELECT * FROM product";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            numOfAllProducts ++;
        }
        return numOfAllProducts;
    }
    public Products[] allProducts() throws SQLException {

        Products [] products = new Products[numOfAllProducts()];
        int i = 0;

        String allProducts = "SELECT * FROM product";
        PreparedStatement preparedStatement = connection.prepareStatement(allProducts);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            int id = resultSet.getInt("product_id");
            String productName1 = resultSet.getString("product_name");
            String product_creat_date = resultSet.getString("product_creat_date");
            int category_id_fk = resultSet.getInt("category_id_fk");
            Category category = categoryService.loadCategoryByID(category_id_fk);
            int brand_id_fk = resultSet.getInt("brand_id_fk");
            Brands brand = brandService.loadBrandById(brand_id_fk);

            Products product = new Products(id,productName1,product_creat_date,category,brand);
            products[i] = product;
            i++;
        }
        return products;
    }
    public int deleteProduct(int id) throws SQLException {

        String deleteProduct = "DELETE FROM product WHERE product_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteProduct);
        preparedStatement.setInt(1,id);
        int result = preparedStatement.executeUpdate();
        return result;
    }
    public int editProductName(int id,String newProductName) throws SQLException {

        String editProductName = "UPDATE product SET product_name = ? WHERE product_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(editProductName);
        preparedStatement.setString(1,newProductName);
        preparedStatement.setInt(2,id);
        int result = preparedStatement.executeUpdate();
        return result;
    }
    public int editProductCreatDate(int id,String newProductCreatDate) throws SQLException {

        java.util.Date myDate = new java.util.Date(newProductCreatDate);
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());

        String editProductCreatDate = "UPDATE product SET product_creat_date = ? WHERE product_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(editProductCreatDate);
        preparedStatement.setDate(1,sqlDate);
        preparedStatement.setInt(2,id);
        int result = preparedStatement.executeUpdate();
        return result;
    }
    public int editProductCategoryId(int id,int newProductCategoryId) throws SQLException {

        String editProductCategoryId = "UPDATE product SET category_id_fk = ? WHERE product_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(editProductCategoryId);
        preparedStatement.setInt(1,newProductCategoryId);
        preparedStatement.setInt(2,id);
        int result = preparedStatement.executeUpdate();
        return result;
    }
    public int editProductBrandId(int id,int newProductBrandId) throws SQLException {

        String editProductBrandId = "UPDATE product SET brand_id_fk = ? WHERE product_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(editProductBrandId);
        preparedStatement.setInt(1,newProductBrandId);
        preparedStatement.setInt(2,id);
        int result = preparedStatement.executeUpdate();
        return result;
    }
}
