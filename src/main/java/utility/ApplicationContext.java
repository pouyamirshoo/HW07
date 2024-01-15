package utility;
import java.sql.Connection;
import Connection.JdbcConnection;
import repository.UserRepository;
import service.UserService;

public class ApplicationContext {

    private static final Connection CONNECTION;
    private static final UserRepository USER_REPOSITORY;
    private static final UserService USER_SERVICE;

    static {
        CONNECTION = JdbcConnection.getConnection();
        USER_REPOSITORY = new UserRepository(CONNECTION);
        USER_SERVICE = new UserService(USER_REPOSITORY);
    }
    public static UserService getUserService(){
        return USER_SERVICE;
    }
}
