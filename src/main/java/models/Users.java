package models;

public class Users {
    int UserId;
    String UserFullName;
    String UserName;
    String UserEmail;
    String UserPassword;

    public Users() {
    }

    public Users(int userId, String userFullName, String userName, String userEmail, String userPassword) {
        UserId = userId;
        UserFullName = userFullName;
        UserName = userName;
        UserEmail = userEmail;
        UserPassword = userPassword;
    }

    public Users(String userFullName, String userName, String userEmail, String userPassword) {
        UserFullName = userFullName;
        UserName = userName;
        UserEmail = userEmail;
        UserPassword = userPassword;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public void setUserFullName(String userFullName) {
        UserFullName = userFullName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }

    public int getUserId() {
        return UserId;
    }

    public String getUserFullName() {
        return UserFullName;
    }

    public String getUserName() {
        return UserName;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    @Override
    public String toString() {
        return "Users{" +
                "UserId=" + UserId +
                ", UserFullName='" + UserFullName + '\'' +
                ", UserName='" + UserName + '\'' +
                ", UserEmail='" + UserEmail + '\'' +
                ", UserPassword='" + UserPassword + '\'' +
                '}';
    }
}
