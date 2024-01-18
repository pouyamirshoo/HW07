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
        System.out.println("2-log IN");
        System.out.println("3-EXIT");
        System.out.println("Choose your number:");

        int number = sc.nextInt();
        sc.nextLine();

        switch (number) {
            case 1 -> signIn();
            case 2 -> logIn();
            case 3 -> System.out.println("exit");
        }
    }

    public void signIn() throws SQLException {
        userService.saveUser();
    }
    public void logIn() throws SQLException {
        userService.loadUser();
    }

}
