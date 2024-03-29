package service;

import models.Users;
import repository.UserRepository;
import utility.Validation;

import java.sql.SQLException;
import java.util.Scanner;

public class UserService {
    private final Scanner sc = new Scanner(System.in);
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    private String getPasswordFromUser() {
        String password;
        while (true) {
            System.out.println("Please enter your password:");
            System.out.println("Hint: it has to be between 8 to 10 and must contain at least 1 lower and upper case and 1 digit and 1 char ");
            password = sc.nextLine();
            if(Validation.isValidPassword(password))
                break;
            else
                System.out.println("plz enter a valid password");
        }
        return password;
    }

    private String getEmailFromUser() {
        String email;
        while (true) {
            System.out.println("Please enter your email:");
            email = sc.nextLine();
            if(Validation.isValidEmail(email))
                break;
            else
                System.out.println("plz enter a valid email");
        }
        return email;
    }
    public int saveUser() throws SQLException {
        int save = 0;
        System.out.println("plz enter your full name");
        String fullName = sc.nextLine();
        System.out.println("plz enter your username");
        String username = sc.nextLine();
        String email = getEmailFromUser();
        String password = getPasswordFromUser();
        Users user = new Users(fullName,username,email,password);
       int signIn =  userRepository.save(user);
        if(signIn == 1){
            save = 1;
        }
        return save;
    }
    public int loadUser() throws SQLException {
        System.out.println("plz enter your user name");
        String username = sc.nextLine();
        System.out.println("plz enter your password");
        String password = sc.nextLine();

        int loud = 0;

        Users user = userRepository.load(username);
        if(user == null)
            System.out.println("wrong username or sign in first");
        else if (!user.getUserPassword().equals(password))
            System.out.println("wrong password");
        else {
            loud = 1;
            System.out.println("welcome");
            System.out.println(user.toString());
        }
        return loud;
    }
}
