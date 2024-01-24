package service;

import models.Brands;
import models.Category;
import models.Products;
import repository.ProductRepository;
import utility.ApplicationContext;

import java.sql.SQLException;
import java.util.Scanner;

public class ProductService {
    private final Scanner sc = new Scanner(System.in);
    private final ProductRepository productRepository;
    private final CategoryService categoryService = ApplicationContext.getCategoryService();
    private final BrandService brandService = ApplicationContext.getBrandService();

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public void saveProduct() throws SQLException {
        System.out.println("plz enter your product name");
        String productName = sc.nextLine();
        System.out.println("plz enter your date of creat as MM/dd/yyyy: ");
        String date = sc.nextLine();
        System.out.println("plz enter yor category id");
        int categoryId = sc.nextInt();
        sc.nextLine();
        System.out.println("plz enter yor brand id");
        int brandId = sc.nextInt();
        sc.nextLine();
        Category category = categoryService.getCategory(categoryId);
        Brands brand = brandService.getBrand(brandId);

        Products product = new Products(productName,date,category,brand);
        int signIn =  productRepository.save(product);
        if(signIn == 1)
            System.out.println("thank you for make a product");
        else
            System.out.println("this product already made");
    }
    public void loadOneProduct() throws SQLException {
        System.out.println("plz enter your product name");
        String productName = sc.nextLine();
        Products product = productRepository.load(productName);
        if(product != null)
            System.out.println(product.toString());
        else
            System.out.println("enter a valid product name or make one product");
    }
    public void showAllProducts() throws SQLException {
        Products [] products = productRepository.allProducts();
        for (int i = 0; i < products.length; i++) {
            System.out.println(products[i].toString());
        }
    }
    public void deleteOneProduct() throws SQLException {
        System.out.println("plz enter the id of the product you want to delete");
        int del = sc.nextInt();
        sc.nextLine();
        int res = productRepository.deleteProduct(del);
        if(res == 1)
            System.out.println("the product deleted");
        else
            System.out.println("wrong id");
    }
    public void editProductName() throws SQLException {
        System.out.println("plz enter the id of the product you want to edit name");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("plz enter the new name for product");
        String newName = sc.nextLine();
        int res = productRepository.editProductName(id,newName);
        if(res == 1)
            System.out.println("edit complete");
        else
            System.out.println("edit failed,try again");
    }
    public void editProductCreatDate() throws SQLException {
        System.out.println("plz enter the id of the product you want to edit creat date");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("plz enter the new creat date as mm/dd/yyyy for product");
        String newCreatDate = sc.nextLine();
        int res = productRepository.editProductCreatDate(id,newCreatDate);
        if(res == 1)
            System.out.println("edit complete");
        else
            System.out.println("edit failed,try again");
    }
    public void editProductCategoryId() throws SQLException {
        System.out.println("plz enter the id of the product you want to edit category id");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("plz enter the new category id");
        int newCategoryId = sc.nextInt();
        sc.nextLine();
        int res = productRepository.editProductCategoryId(id,newCategoryId);
        if(res == 1)
            System.out.println("edit complete");
        else
            System.out.println("edit failed,try again");
    }
    public void editProductBrandId() throws SQLException {
        System.out.println("plz enter the id of the product you want to edit brand id");
        int id = sc.nextInt();
        sc.nextLine();
        brandService.loadAllBrands();
        System.out.println("plz enter the new brand id");
        int newBrandId = sc.nextInt();
        sc.nextLine();
        int res = productRepository.editProductBrandId(id,newBrandId);
        if(res == 1)
            System.out.println("edit complete");
        else
            System.out.println("edit failed,try again");
    }

}
