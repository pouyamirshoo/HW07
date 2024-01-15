package service;

import repository.UserRepository;
import utility.Validation;

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
}
