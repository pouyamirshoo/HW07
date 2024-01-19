package utility;
import java.sql.Connection;
import Connection.JdbcConnection;

import repository.BrandRepository;
import repository.CategoryRepository;
import repository.ProductRepository;
import repository.UserRepository;
import service.BrandService;
import service.CategoryService;
import service.ProductService;
import service.UserService;

public class ApplicationContext {

    private static final Connection CONNECTION;
    private static final UserRepository USER_REPOSITORY;
    private static final UserService USER_SERVICE;
    private static final BrandRepository BRAND_REPOSITORY;
    private static final BrandService BRAND_SERVICE;
    private static final CategoryRepository CATEGORY_REPOSITORY;
    private static final CategoryService CATEGORY_SERVICE;
    private static final ProductRepository PRODUCT_REPOSITORY;
    private static final ProductService PRODUCT_SERVICE;


    static {
        CONNECTION = JdbcConnection.getConnection();
        USER_REPOSITORY = new UserRepository(CONNECTION);
        USER_SERVICE = new UserService(USER_REPOSITORY);
        BRAND_REPOSITORY = new BrandRepository(CONNECTION);
        BRAND_SERVICE = new BrandService(BRAND_REPOSITORY);
        CATEGORY_REPOSITORY = new CategoryRepository(CONNECTION);
        CATEGORY_SERVICE = new CategoryService(CATEGORY_REPOSITORY);
        PRODUCT_REPOSITORY = new ProductRepository(CONNECTION);
        PRODUCT_SERVICE = new ProductService(PRODUCT_REPOSITORY);

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
    public static ProductService getProductService (){
        return PRODUCT_SERVICE;
    }


}
