package menu;

import service.*;
import utility.ApplicationContext;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    private final Scanner sc =new Scanner(System.in);
    private final UserService userService = ApplicationContext.getUserService();
    private final BrandService brandService = ApplicationContext.getBrandService();
    private final CategoryService categoryService = ApplicationContext.getCategoryService();
    private final ProductService productService = ApplicationContext.getProductService();
    private final ShareholderService shareholderService = ApplicationContext.getShareholderService();
    private final Shareholder_brandsService shareholderBrandsService = ApplicationContext.getShareholderBrandsService();

    public void publicMenu() throws SQLException {
        System.out.println("***** WELCOME *****");
        System.out.println("1-SIGN IN");
        System.out.println("2-LOG IN");
        System.out.println("3-BRAND");
        System.out.println("4-CATEGORY");
        System.out.println("5-PRODUCT");
        System.out.println("6-SHAREHOLDER");
        System.out.println("Choose your number:");

        int number = sc.nextInt();
        sc.nextLine();

        switch (number) {
            case 1 -> signIn();
            case 2 -> logIn();
            case 3 -> brand();
            case 4 -> category();
            case 5 -> product();
            case 6 -> shareholder();
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
        System.out.println("press 5 for show all shareholders of one brand");
        int brandCh = sc.nextInt();
        sc.nextLine();
        switch (brandCh){
            case 1 -> brandService.saveBrand();
            case 2 -> brandService.loadBrand();
            case 3 -> delBrand();
            case 4 -> editBrand();
            case 5 -> shareholdersOfOneBrand();
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
    public void shareholdersOfOneBrand() throws SQLException {
        brandService.shareholdersOfOneBrand();
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
        System.out.println("press 3 for delete a product");
        System.out.println("press 4 for edit a product");
        int productCH = sc.nextInt();
        sc.nextLine();
        switch (productCH){
            case 1 -> makeProduct();
            case 2 -> checkProduct();
            case 3 -> deleteProduct();
            case 4 -> editProduct();
        }
    }
    public void makeProduct() throws SQLException {
        showAllBrAndCa();
        productService.saveProduct();
    }
    public void checkProduct() throws SQLException {
        productService.loadOneProduct();
    }
    public void deleteProduct() throws SQLException {
        productService.showAllProducts();
        productService.deleteOneProduct();
    }
    public void editProduct() throws SQLException {
        System.out.println("all products");
        productService.showAllProducts();
        System.out.println("press 1 for edit product name");
        System.out.println("press 2 for edit product creat date");
        System.out.println("press 3 for edit product category id");
        System.out.println("press 4 for edit product brand id");
        int editPr = sc.nextInt();
        sc.nextLine();
        switch (editPr){
            case 1 -> productService.editProductName();
            case 2 -> productService.editProductCreatDate();
            case 3 -> productService.editProductCategoryId();
            case 4 -> productService.editProductBrandId();
        }
    }
    public void shareholder() throws SQLException {
        System.out.println("press 1 to make a shareholder");
        System.out.println("press 2 to loud a shareholder");
        System.out.println("press 3 to see all brands holds by one shareholder");
        System.out.println("press 4 to delete one shareholder");
        System.out.println("press 5 to edit one shareholder");
        int shNum = sc.nextInt();
        sc.nextLine();
        switch (shNum){
            case 1 -> saveShareHolder();
            case 2 -> loudOneShareHolder();
            case 3 -> brandsOfOneShareHolder();
            case 4 -> deleteOneShaeHolder();
            case 5 -> editShareHolder();
        }

    }
    public void saveShareHolder() throws SQLException {
        shareholderService.saveShareHolder();
    }
    public void loudOneShareHolder() throws SQLException {
        shareholderService.loudOneShareHolder();
    }
    public void brandsOfOneShareHolder() throws SQLException {
        shareholderService.brandsOfOneShareHolder();
    }
    public void deleteOneShaeHolder() throws SQLException {
        shareholderService.deleteOneShareHolder();
    }
    public void editShareHolder() throws SQLException {
        System.out.println("press 1 to edit name");
        System.out.println("press 2 to edit phone number");
        System.out.println("press 3 to edit national code");
        System.out.println("press 4 to edit brand");
        int edit = sc.nextInt();
        sc.nextLine();
        switch (edit){
            case 1 -> shareholderService.editShareHolderName();
            case 2 -> shareholderService.editShareHolderPhone();
            case 3 -> shareholderService.editShareHolderCode();
            case 4 -> shareholderService.editShareHolderBrand();

        }
    }
}
