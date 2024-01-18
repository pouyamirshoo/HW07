package utility;
import java.sql.Connection;
import Connection.JdbcConnection;
import menu.Menu;
import repository.BrandRepository;
import repository.UserRepository;
import service.BrandService;
import service.UserService;

public class ApplicationContext {

    private static final Connection CONNECTION;
    private static final UserRepository USER_REPOSITORY;
    private static final UserService USER_SERVICE;
    private static final BrandRepository BRAND_REPOSITORY;
    private static final BrandService BRAND_SERVICE;
    private static final Menu MENU;

    static {
        CONNECTION = JdbcConnection.getConnection();
        USER_REPOSITORY = new UserRepository(CONNECTION);
        USER_SERVICE = new UserService(USER_REPOSITORY);
        BRAND_REPOSITORY = new BrandRepository(CONNECTION);
        BRAND_SERVICE = new BrandService(BRAND_REPOSITORY);
        MENU = new Menu(USER_SERVICE);
    }
    public static Menu getMenu(){
        return MENU;
    }

}
