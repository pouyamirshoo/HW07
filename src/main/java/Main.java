import menu.Menu;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        Scanner sc = new Scanner(System.in);
        Menu menu = new Menu();

        menu.publicMenu();

        System.out.println("do you want to continue?(y,n)");
        String continueMain = sc.nextLine();
        continueMain.toLowerCase();
        if(continueMain.charAt(0) == 'y'){
            System.out.println();
            main(args);
        }
    }
}
