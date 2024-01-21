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
}
