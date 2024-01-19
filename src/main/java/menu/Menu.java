package menu;

import service.BrandService;
import service.CategoryService;
import service.ProductService;
import service.UserService;
import utility.ApplicationContext;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    private final Scanner sc =new Scanner(System.in);
    private final UserService userService = ApplicationContext.getUserService();
    private final BrandService brandService = ApplicationContext.getBrandService();
    private final CategoryService categoryService = ApplicationContext.getCategoryService();
    private final ProductService productService = ApplicationContext.getProductService();

    public void publicMenu() throws SQLException {
        System.out.println("***** WELCOME *****");
        System.out.println("1-SIGN IN");
        System.out.println("2-LOG IN");
        System.out.println("3-BRAND");
        System.out.println("4-CATEGORY");
        System.out.println("5-PRODUCT");
        System.out.println("Choose your number:");

        int number = sc.nextInt();
        sc.nextLine();

        switch (number) {
            case 1 -> signIn();
            case 2 -> logIn();
            case 3 -> brand();
            case 4 -> category();
            case 5 -> product();
        }
    }

    public void signIn() throws SQLException {
        userService.saveUser();
    }
    public void logIn() throws SQLException {
        userService.loadUser();
    }
    public void brand() throws SQLException {
        System.out.println("press 1 for make brand");
        System.out.println("press 2 for check brand");
        System.out.println("press 3 for delete one brand");
        System.out.println("press 4 for edit brand");
        int brandCh = sc.nextInt();
        sc.nextLine();
        switch (brandCh){
            case 1 -> brandService.saveBrand();
            case 2 -> brandService.loadBrand();
            case 3 -> delBrand();
            case 4 -> editBrand();
        }
    }
    public void delBrand() throws SQLException {
        System.out.println("all brands");
        brandService.loadAllBrands();
        brandService.deleteOneBrand();
    }
    public void editBrand() throws SQLException {
        System.out.println("all brands");
        brandService.loadAllBrands();
        System.out.println("press 1 for edit brand name");
        System.out.println("press 2 for edit brand website");
        System.out.println("press 3 for edit brand description");
        int edt = sc.nextInt();
        sc.nextLine();
        switch (edt){
            case 1 -> brandService.editBrandName();
            case 2 -> brandService.editBrandWebsite();
            case 3 -> brandService.editBrandDescription();
        }
    }
    public void category() throws SQLException {
        System.out.println("press 1 for make category");
        System.out.println("press 2 for check category");
        System.out.println("press 3 for delete one category");
        System.out.println("press 4 for edit category");
        int categoryCh = sc.nextInt();
        sc.nextLine();
        switch (categoryCh){
            case 1 -> categoryService.saveCategory();
            case 2 -> categoryService.loadCategory();
            case 3 -> delCategory();
            case 4 -> editCategory();
        }
    }
    public void delCategory() throws SQLException {
        System.out.println("all categories");
        categoryService.loadAllCategories();
        categoryService.deleteOneCategory();
    }
    public void editCategory() throws SQLException {
        System.out.println("all categories");
        categoryService.loadAllCategories();
        System.out.println("press 1 for edit category name");
        System.out.println("press 2 for edit category description");
        int edt = sc.nextInt();
        sc.nextLine();
        switch (edt) {
            case 1 -> categoryService.editCategoryName();
            case 2 -> categoryService.editCategoryDescription();
        }
    }
    public void showAllBrAndCa() throws SQLException {
        System.out.println("all brands:");
        brandService.loadAllBrands();
        System.out.println("*******************");
        System.out.println("all categories");
        categoryService.loadAllCategories();

    }
    public void product() throws SQLException {
        System.out.println("press 1 for make a product");
        System.out.println("press 2 for check a product");
        int productCH = sc.nextInt();
        sc.nextLine();
        switch (productCH){
            case 1 -> makeProduct();
            case 2 -> checkProduct();
        }
    }
    public void makeProduct() throws SQLException {
        showAllBrAndCa();
        productService.saveProduct();
    }
    public void checkProduct() throws SQLException {
        productService.loadOneProduct();
    }
}
