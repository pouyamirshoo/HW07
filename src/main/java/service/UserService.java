package service;

import feilds.Users;
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
    public void saveUser() throws SQLException {
        System.out.println("plz enter your full name");
        String fullName = sc.nextLine();
        System.out.println("plz enter your username");
        String username = sc.nextLine();
        String email = getEmailFromUser();
        String password = getPasswordFromUser();
        Users user = new Users(fullName,username,email,password);
       int signIn =  userRepository.save(user);
        if(signIn == 1){
            System.out.println("thank you for sign in");
        }
        else
            System.out.println("you already sign in");
    }
}
