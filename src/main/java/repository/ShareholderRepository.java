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
        return preparedStatement.executeUpdate();
    }
    public Shareholder takeOBJid(Shareholder shareholder) throws SQLException {

        String sql = "SELECT * FROM shareholder WHERE shareholder_name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, shareholder.getShareHolderName());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int fk = resultSet.getInt("shareholder_id");
            return new Shareholder(fk);
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
            return new Shareholder(fk,name1,phone,code);
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
            return new Shareholder(fk,name1,phone,code);
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
    public int numOfArraySh() throws SQLException {
        String numOfArraySh = "SELECT * FROM shareholder";
        PreparedStatement preparedStatement = connection.prepareStatement(numOfArraySh);
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
    public Shareholder [] showAllShareHolders() throws SQLException {
        Shareholder [] shareholders = new Shareholder[numOfArraySh()];

        String numOfArraySh = "SELECT * FROM shareholder";
        PreparedStatement preparedStatement = connection.prepareStatement(numOfArraySh);
        ResultSet resultSet = preparedStatement.executeQuery();
        int i = 0;
        while (resultSet.next()) {
            int id = resultSet.getInt("shareholder_id");
            String name = resultSet.getString("shareholder_name");
            String phone = resultSet.getString("shareholder_phone_number");
            String code = resultSet.getString("shareholder_national_code");
            Shareholder shareholder = new Shareholder(id,name,phone,code);
            shareholders [i] = shareholder;
            i++;
        }
        return shareholders;

    }
    public int deleteShareholder(int id) throws SQLException {

        String deleteShareholder = "DELETE FROM shareholder WHERE shareholder_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteShareholder);
        preparedStatement.setInt(1,id);
        return preparedStatement.executeUpdate();
    }
    public int deleteShareholderFromInnerTable(int id) throws SQLException {

        String deleteShareholderFromInnerTable = "DELETE FROM shareholder_brand WHERE shareholder_id_fk = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteShareholderFromInnerTable);
        preparedStatement.setInt(1,id);
        return preparedStatement.executeUpdate();
    }
    public int editShareHolderName(int id,String newShareholderName) throws SQLException {

        String editShareHolderName = "UPDATE shareholder SET shareholder_name = ? WHERE shareholder_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(editShareHolderName);
        preparedStatement.setString(1,newShareholderName);
        preparedStatement.setInt(2,id);
        return preparedStatement.executeUpdate();
    }
    public int editShareHolderPhone(int id,String newShareholderPhone) throws SQLException {

        String editShareHolderPhone = "UPDATE shareholder SET shareholder_phone_number = ? WHERE shareholder_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(editShareHolderPhone);
        preparedStatement.setString(1,newShareholderPhone);
        preparedStatement.setInt(2,id);
        return preparedStatement.executeUpdate();
    }
    public int editShareHolderCode(int id,String newShareholderCode) throws SQLException {

        String editShareHolderCode = "UPDATE shareholder SET shareholder_national_code = ? WHERE shareholder_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(editShareHolderCode);
        preparedStatement.setString(1,newShareholderCode);
        preparedStatement.setInt(2,id);
        return preparedStatement.executeUpdate();
    }
    public int editShareHolderBrands(int id,int del,int fk) throws SQLException {

        String editShareHolderBrands ="UPDATE shareholder_brand SET brand_id_fk = ? WHERE shareholder_id_fk = ? AND brand_id_fk = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(editShareHolderBrands);
        preparedStatement.setInt(1,fk);
        preparedStatement.setInt(2,id);
        preparedStatement.setInt(3,del);
        return preparedStatement.executeUpdate();
    }
    public int numOfArrayB(int id) throws SQLException {
        String numOfArrayB = "SELECT * FROM shareholder_brand WHERE brand_id_fk = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(numOfArrayB);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        int i = 0;
        while (resultSet.next()) {
            i++;
        }
        return i;
    }
    public Shareholder [] brandsShareHolder(int id) throws SQLException {

        Shareholder [] shareholders = new Shareholder[numOfArrayB(id)];

        String brandsShareHolder = "SELECT * FROM shareholder_brand WHERE brand_id_fk = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(brandsShareHolder);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        int i = 0;
        while (resultSet.next()){
            int fk = resultSet.getInt("shareholder_id_fk");
            shareholders [i] = loudById(fk);
            i++;
        }
        return shareholders;
    }
    public int addOneBrand(int id,int Bid) throws SQLException {

        String addOneBrand = "INSERT INTO shareholder_brand(shareholder_id_fk,brand_id_fk) VALUES(?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(addOneBrand);
        preparedStatement.setInt(1, id);
        preparedStatement.setInt(2, Bid);
        return preparedStatement.executeUpdate();
    }
}
