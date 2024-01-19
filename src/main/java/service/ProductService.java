package service;

import feilds.Brands;
import feilds.Category;
import feilds.Products;
import repository.CategoryRepository;
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

}
