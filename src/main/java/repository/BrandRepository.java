package repository;

import feilds.Brands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BrandRepository {
    private final Connection connection;

    public BrandRepository(Connection connection) {
        this.connection = connection;
    }

    public int save(Brands brand) throws SQLException {

        String saveBrand = "INSERT INTO brand(brand_name,brand_website,brand_description) VALUES(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(saveBrand);
        preparedStatement.setString(1, brand.getBrandName());
        preparedStatement.setString(2, brand.getWebsite());
        preparedStatement.setString(3, brand.getDescription());
        int result = preparedStatement.executeUpdate();
        return result;
    }

    public int numOfLoadAll() throws SQLException {

        String numOfLoadAllBrands = "SELECT * FROM brand ";
        PreparedStatement preparedStatement = connection.prepareStatement(numOfLoadAllBrands);
        ResultSet resultSet = preparedStatement.executeQuery();
        int num = 0;
        while (resultSet.next()) {
            num++;
        }
        return num;
    }

    public Brands[] loadAll() throws SQLException {

        Brands [] brands = new Brands[numOfLoadAll()];
        int i = 0;

        String loadAllBrands = "SELECT * FROM brand ";
        PreparedStatement preparedStatement = connection.prepareStatement(loadAllBrands);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("brand_id");
            String brandName1 = resultSet.getString("brand_name");
            String brandWebsite = resultSet.getString("brand_website");
            String brandDescription = resultSet.getString("brand_description");
            Brands brand = new Brands(id, brandName1, brandWebsite, brandDescription);
            brands [i] = brand;
        }
        return brands;
    }
    public Brands load(String brandName) throws SQLException {

        String loadBrand = "SELECT * FROM brand WHERE brand_name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(loadBrand);
        preparedStatement.setString(1, brandName);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int id = resultSet.getInt("brand_id");
            String brandName1 = resultSet.getString("brand_name");
            String brandWebsite = resultSet.getString("brand_website");
            String brandDescription = resultSet.getString("brand_description");
            Brands brand = new Brands(id,brandName1,brandWebsite,brandDescription);
            return brand;
        } else
            return null;
    }
}