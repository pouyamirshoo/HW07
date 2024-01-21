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
}
