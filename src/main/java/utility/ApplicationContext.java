package utility;
import java.sql.Connection;
import Connection.JdbcConnection;
import repository.UserRepository;

public class ApplicationContext {

    private static final Connection CONNECTION;
    private static final UserRepository USER_REPOSITORY;

    static {
        CONNECTION = JdbcConnection.getConnection();
        USER_REPOSITORY = new UserRepository(CONNECTION);
    }
}
