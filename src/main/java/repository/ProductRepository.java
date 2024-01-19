package repository;

import feilds.Products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductRepository {
    private final Connection connection;

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
}
