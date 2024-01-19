package service;

import feilds.Category;
import repository.CategoryRepository;
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
        if (category == null)
            System.out.println("wrong brand name or make a brand first");
        else {
            System.out.println("welcome");
            System.out.println(category.toString());
        }
    }
        public Category loadCategoryByID (int id) throws SQLException {

        Category category1 = categoryRepository.loadById(id);
        return category1;
    }
    public void loadAllCategories() throws SQLException {
        Category[]categories = categoryRepository.loadAll();
        for (int i = 0; i < categories.length; i++) {
            System.out.println(categories[i].toString());
        }
    }
    public Category getCategory(int id) throws SQLException {
       Category category = categoryRepository.takeCategory(id);
       return category;
    }
    public void deleteOneCategory() throws SQLException {
        System.out.println("plz enter the category id you want to delete");
        int id = sc.nextInt();
        sc.nextLine();
        int del = categoryRepository.deleteCategory(id);
        if(del == 1)
            System.out.println("the category has been deleted");
        else
            System.out.println("enter the valid id");
    }
    public void editCategoryName() throws SQLException {
        System.out.println("plz enter your category id");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("plz enter your new category name");
        String newCategoryName = sc.nextLine();
        int edit =categoryRepository.editCategoryName (id,newCategoryName);
        if(edit == 1)
            System.out.println("your category name has changed");
        else
            System.out.println("try again");
    }
    public void editCategoryDescription() throws SQLException {
        System.out.println("plz enter your category id");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("plz enter your new category description");
        String newCategoryDescription = sc.nextLine();
        int edit =categoryRepository.editCategoryDescription(id,newCategoryDescription);
        if(edit == 1)
            System.out.println("your category description has changed");
        else
            System.out.println("try again");
    }
}
