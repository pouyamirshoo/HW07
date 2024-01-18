package repository;

import feilds.Brands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BrandRepository {
    private final Connection connection;

    public BrandRepository(Connection connection) {
        this.connection = connection;
    }

    public int save(Brands brand) throws SQLException {

        String saveBrand = "INSERT INTO brand(brand_name,brand_website,brand_description) VALUES(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(saveBrand);
        preparedStatement.setString(1,brand.getBrandName());
        preparedStatement.setString(2,brand.getWebsite());
        preparedStatement.setString(3,brand.getDescription());
        int result = preparedStatement.executeUpdate();
        return result;
    }
}
