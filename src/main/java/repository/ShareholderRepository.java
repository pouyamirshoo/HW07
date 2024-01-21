package repository;

import models.Brands;
import models.Shareholder;
import service.BrandService;
import utility.ApplicationContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShareholderRepository {
    private final Connection connection;
    private final BrandService brandService = ApplicationContext.getBrandService();

    public ShareholderRepository(Connection connection) {
        this.connection = connection;
    }
    public int save(Shareholder shareholder) throws SQLException {

        String save = "INSERT INTO shareholder(shareholder_name,shareholder_phone_number,shareholder_national_code) VALUES(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(save);
        preparedStatement.setString(1,shareholder.getShareHolderName());
        preparedStatement.setString(2,shareholder.getShareHolderPhoneNumber());
        preparedStatement.setString(3,shareholder.getShareHolderNationalCode());
        int result = preparedStatement.executeUpdate();
        return result;
    }
    public Shareholder takeOBJid(Shareholder shareholder) throws SQLException {

        String sql = "SELECT * FROM shareholder WHERE shareholder_name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, shareholder.getShareHolderName());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int fk = resultSet.getInt("shareholder_id");
            Shareholder shareholder1 = new Shareholder(fk);
            return shareholder1;
        }
        else
            return null;
    }
    public void saveInMidTable(Shareholder shareholder,Shareholder shareholder1) throws SQLException {
        int fk = shareholder.getShareHolderId();
        Brands[] brands = shareholder1.getBrands();
        for (int i = 0; i < brands.length; i++) {
            int fk2 = brands[i].getBrandId();
            String SQL = "INSERT INTO shareholder_brand(shareholder_id_fk,brand_id_fk) VALUES(?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, fk);
            preparedStatement.setInt(2, fk2);
            int result = preparedStatement.executeUpdate();
        }
    }
    public Shareholder loud(String name) throws SQLException {

        String loud = "SELECT * FROM shareholder WHERE shareholder_name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(loud);
        preparedStatement.setString(1,name);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int fk = resultSet.getInt("shareholder_id");
            String name1 = resultSet.getString("shareholder_name");
            String phone = resultSet.getString("shareholder_phone_number");
            String code = resultSet.getString("shareholder_national_code");
            Shareholder shareholder = new Shareholder(fk,name1,phone,code);
            return shareholder;
        }
        else
            return null;
    }
    public Shareholder loudById(int id) throws SQLException {

        String loudById = "SELECT * FROM shareholder WHERE shareholder_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(loudById);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int fk = resultSet.getInt("shareholder_id");
            String name1 = resultSet.getString("shareholder_name");
            String phone = resultSet.getString("shareholder_phone_number");
            String code = resultSet.getString("shareholder_national_code");
            Shareholder shareholder = new Shareholder(fk,name1,phone,code);
            return shareholder;
        }
        else
            return null;
    }
    public int numOfArray(int id) throws SQLException {
        String shareholderBrands = "SELECT * FROM shareholder_brand WHERE shareholder_id_fk = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(shareholderBrands);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        int i = 0;
        while (resultSet.next()) {
            i++;
        }
        return i;
    }
    public Brands[] shareholderBrands(int id) throws SQLException {

        Brands [] brands = new Brands[numOfArray(id)];

        String shareholderBrands = "SELECT * FROM shareholder_brand WHERE shareholder_id_fk = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(shareholderBrands);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        int i = 0;
        while (resultSet.next()){
            int fk = resultSet.getInt("brand_id_fk");
            brands[i] = brandService.loadBrandById(fk);
            i++;
        }
        return brands;
    }
}
