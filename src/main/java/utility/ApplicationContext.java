package utility;
import java.sql.Connection;
import Connection.JdbcConnection;

import repository.BrandRepository;
import repository.CategoryRepository;
import repository.UserRepository;
import service.BrandService;
import service.CategoryService;
import service.UserService;

public class ApplicationContext {

    private static final Connection CONNECTION;
    private static final UserRepository USER_REPOSITORY;
    private static final UserService USER_SERVICE;
    private static final BrandRepository BRAND_REPOSITORY;
    private static final BrandService BRAND_SERVICE;
    private static final CategoryRepository CATEGORY_REPOSITORY;
    private static final CategoryService CATEGORY_SERVICE;


    static {
        CONNECTION = JdbcConnection.getConnection();
        USER_REPOSITORY = new UserRepository(CONNECTION);
        USER_SERVICE = new UserService(USER_REPOSITORY);
        BRAND_REPOSITORY = new BrandRepository(CONNECTION);
        BRAND_SERVICE = new BrandService(BRAND_REPOSITORY);
        CATEGORY_REPOSITORY = new CategoryRepository(CONNECTION);
        CATEGORY_SERVICE = new CategoryService(CATEGORY_REPOSITORY);

    }
    public static UserService getUserService (){
        return USER_SERVICE;
    }
    public static BrandService getBrandService (){
        return BRAND_SERVICE;
    }
    public static CategoryService getCategoryService (){
        return CATEGORY_SERVICE;
    }


}
