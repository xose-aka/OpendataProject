package uzb.progressive_young_team.opendataproject;

import java.sql.Timestamp;

public class User {

    private String userName, userId, userSurname, userPassword, dataRegistration, userPhoneNumber;

    public User() {}

    public User(String userName, String userId, String userSurname, String userPassword, String dataRegistration, String userPhoneNumber) {
        this.userName = userName;
        this.userId = userId;
        this.userSurname = userSurname;
        this.userPassword = userPassword;
        this.dataRegistration = dataRegistration;
        this.userPhoneNumber = userPhoneNumber;

    }

    public String getmUserName() {
        return userName;
    }

    public String getmUserId() {
        return userId;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public String getmUserPassword() {
        return userPassword;
    }

    public String getmDateRegistration() {
        return dataRegistration;
    }

    public String getmUserPhoneNumber() {
        return userPhoneNumber;
    }
}
