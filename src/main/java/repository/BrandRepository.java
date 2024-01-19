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
            i++;
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
    public Brands takeBrand(int id) throws SQLException {

        String takeBrand = "SELECT * FROM brand WHERE brand_id =?";
        PreparedStatement preparedStatement = connection.prepareStatement(takeBrand);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int Bid = resultSet.getInt("brand_id");
            String brandName = resultSet.getString("brand_name");
            String brandWebsite = resultSet.getString("brand_website");
            String brandDescription = resultSet.getString("brand_description");
            Brands brand = new Brands(Bid,brandName,brandWebsite,brandDescription);
            return brand;
        } else
            return null;
    }
    public int deleteBrand(int id) throws SQLException {

        String deleteBrand = "DELETE FROM brand WHERE brand_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteBrand);
        preparedStatement.setInt(1,id);
        int result = preparedStatement.executeUpdate();
        return result;
    }
    public int editBrandName(int id,String newBrandName) throws SQLException {

        String editBrandName = "UPDATE brand SET brand_name = ? WHERE brand_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(editBrandName);
        preparedStatement.setString(1,newBrandName);
        preparedStatement.setInt(2,id);
        int result = preparedStatement.executeUpdate();
        return result;
    }
    public int editBrandWebsite(int id,String newBrandWebsite) throws SQLException {

        String editBrandWebsite = "UPDATE brand SET brand_website = ? WHERE brand_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(editBrandWebsite);
        preparedStatement.setString(1,newBrandWebsite);
        preparedStatement.setInt(2,id);
        int result = preparedStatement.executeUpdate();
        return result;
    }
}
