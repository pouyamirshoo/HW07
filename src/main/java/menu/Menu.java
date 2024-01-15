package menu;

import service.UserService;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    private final Scanner sc =new Scanner(System.in);
    private final UserService userService;

    public Menu(UserService userService) {
        this.userService = userService;
    }

    public void publicMenu() throws SQLException {
        System.out.println("***** WELCOME *****");
        System.out.println("1-SIGN UP");
        System.out.println("2-SIGN IN");
        System.out.println("3-EXIT");
        System.out.println("Choose your number:");

        int number = sc.nextInt();
        sc.nextLine();

        switch (number) {
            case 1 -> signUp();
            case 2 -> System.out.println("hi");
            case 3 -> System.out.println("exit");
        }
    }

    public void signUp() throws SQLException {
        userService.saveUser();
    }

}
