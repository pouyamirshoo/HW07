package repository;

import models.Shareholder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Shareholder_brandsRepository {
    private final Connection connection;

    public Shareholder_brandsRepository(Connection connection) {
        this.connection = connection;
    }

    public int numOfShareHolders(int id) throws SQLException {

        int i = 0;

        String numOfShareHolders = "SELECT * FROM shareholder_brand WHERE brnad_id_fk = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(numOfShareHolders);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            i++;
        }
        return i;
    }
    public int [] allBrandShareHolders(int id) throws SQLException {

        int [] shareholders = new int[numOfShareHolders(id)];
        int j = 0;

        String numOfShareHolders = "SELECT * FROM shareholder_brand WHERE brnad_id_fk = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(numOfShareHolders);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
           int fk = resultSet.getInt("shareholder_id_fk");
           shareholders [j] = fk;
            j++;
        }
        return shareholders;
    }
}