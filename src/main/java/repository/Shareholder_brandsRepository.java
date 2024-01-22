package repository;

import java.sql.Connection;

public class Shareholder_brandsRepository {
    private final Connection connection;

    public Shareholder_brandsRepository(Connection connection) {
        this.connection = connection;
    }
}
