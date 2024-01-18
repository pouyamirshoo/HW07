package service;


import feilds.Category;
import repository.CategoryRepository;
import utility.Validation;

import java.sql.SQLException;
import java.util.Scanner;

public class CategoryService {
    private final Scanner sc = new Scanner(System.in);
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void saveCategory() throws SQLException {
        System.out.println("plz enter your category name");
        String categoryName = sc.nextLine();
        System.out.println("plz enter your description for category");
        String description = sc.nextLine();

        Category category = new Category(categoryName,description);
        int signIn =  categoryRepository.save(category);
        if(signIn == 1){
            System.out.println("thank you for make a category");
        }
        else
            System.out.println("this category already made");
    }
    public void loadCategory() throws SQLException {
        System.out.println("plz enter your category name");
        String categoryName = sc.nextLine();

        Category category = categoryRepository.load(categoryName);
        if(category == null)
            System.out.println("wrong brand name or make a brand first");
        else {
            System.out.println("welcome");
            System.out.println(category.toString());
        }
    }
}
