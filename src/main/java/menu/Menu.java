package menu;

import service.BrandService;
import service.UserService;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    private final Scanner sc =new Scanner(System.in);
    private  UserService userService;
    private  BrandService brandService;

    public Menu(UserService userService) {
        this.userService = userService;
    }

    public Menu(BrandService brandService) {
        this.brandService = brandService;
    }

    public void publicMenu() throws SQLException {
        System.out.println("***** WELCOME *****");
        System.out.println("1-SIGN IN");
        System.out.println("2-LOG IN");
        System.out.println("3-BRAND");
        System.out.println("Choose your number:");

        int number = sc.nextInt();
        sc.nextLine();

        switch (number) {
            case 1 -> signIn();
            case 2 -> logIn();
            case 3 -> brand();
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
        int brandCh = sc.nextInt();
        sc.nextLine();
        switch (brandCh){
            case 1 -> brandService.saveBrand();
            case 2 -> brandService.loadBrand();
        }
    }
}
