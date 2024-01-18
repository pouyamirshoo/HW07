package utility;

import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

;

public class Validation {
    private static final Pattern USER_EMAIL;
    private static final Pattern USER_PASSWORD;

    static {
        USER_EMAIL = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
        USER_PASSWORD = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#!%&*])[A-Za-z0-9@#!%&*]{8,10}$");
    }

    public static boolean isValidEmail(String email) {
        Matcher matcher = USER_EMAIL.matcher(email);
        return matcher.find();
    }

    public static boolean isValidPassword(String password) {
        Matcher matcher = USER_PASSWORD.matcher(password);
        return matcher.find();
    }
    public static boolean isValidWebsite(String url){
        try {
            new URL(url).toURI();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
