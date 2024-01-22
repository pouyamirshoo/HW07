package utility;

import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Validation {
    private static final Pattern USER_EMAIL;
    private static final Pattern USER_PASSWORD;
    private static final Pattern PHONE_NUMBER;

    static {
        USER_EMAIL = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
        USER_PASSWORD = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#!%&*])[A-Za-z0-9@#!%&*]{8,10}$");
        PHONE_NUMBER = Pattern.compile("^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$");
    }

    public static boolean isValidEmail(String email) {
        Matcher matcher = USER_EMAIL.matcher(email);
        return matcher.find();
    }

    public static boolean isValidPassword(String password) {
        Matcher matcher = USER_PASSWORD.matcher(password);
        return matcher.find();
    }
    public static boolean isValidPhoneNumber(String phoneNumber) {
        Matcher matcher = PHONE_NUMBER.matcher(phoneNumber);
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
    public static boolean isValidNationalCode(String nationalCode)
    {
        if (!nationalCode.matches("^\\d{10}$"))
            return false;

        int sum = 0;

        for (int i = 0; i < 9; i++)
        {
            sum += Character.getNumericValue(nationalCode.charAt(i)) * (10 - i);
        }

        int lastDigit = Integer.parseInt(String.valueOf(nationalCode.charAt(9)));
        int divideRemaining = sum % 11;

        return ((divideRemaining < 2 && lastDigit == divideRemaining) ||   (divideRemaining >= 2 && (11 - divideRemaining) == lastDigit ));
    }
}
