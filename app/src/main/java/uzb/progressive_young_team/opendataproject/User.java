package uzb.progressive_young_team.opendataproject;

import java.sql.Timestamp;

public class User {

    private String mUserName, mUserId, mUserSurname, mUserPassword;
    private Timestamp mDateRegistration;
    private int mUserPhoneNumber;

    public User() {}

    public User(String mUserName, String mUserId, String mUserSurname, String mUserPassword, Timestamp mDateRegistration, int mUserPhoneNumber) {
        this.mUserName = mUserName;
        this.mUserId = mUserId;
        this.mUserSurname = mUserSurname;
        this.mUserPassword = mUserPassword;
        this.mDateRegistration = mDateRegistration;
        this.mUserPhoneNumber = mUserPhoneNumber;

    }

    public String getmUserName() {
        return mUserName;
    }

    public String getmUserId() {
        return mUserId;
    }

    public String getmUserSurname() {
        return mUserSurname;
    }

    public String getmUserPassword() {
        return mUserPassword;
    }

    public Timestamp getmDateRegistration() {
        return mDateRegistration;
    }

    public int getmUserPhoneNumber() {
        return mUserPhoneNumber;
    }
}
