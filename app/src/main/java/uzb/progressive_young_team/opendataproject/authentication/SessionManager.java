package uzb.progressive_young_team.opendataproject.authentication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

import uzb.progressive_young_team.opendataproject.MainActivity;

public class SessionManager {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;
    private static int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "LOGIN";
    private static final String LOGIN = "IS_LOGIN";
    private static final String PASSWORD = "PASSWORD";
    private static final String PHONE_NUMBER = "PHONE_NUMBER";

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createSession(String phoneNumber, String password) {
        editor.putBoolean(LOGIN, true);
        editor.putString(PHONE_NUMBER, phoneNumber);
        editor.putString(PASSWORD, password);
        editor.apply();
    }

    public boolean isLoggin() {
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    public void checkLogin() {
        if(this.isLoggin()) {
            Intent intent  = new Intent(context, MainActivity.class);
            context.startActivity(intent);
            ((LoginRegisterActivity) context).finish();
        }
    }

    public HashMap<String, String> getUserDetail() {
        HashMap<String, String> user = new HashMap<>();
        user.put(PHONE_NUMBER, sharedPreferences.getString(PHONE_NUMBER, null));
        user.put(PASSWORD, sharedPreferences.getString(PASSWORD, null));
        return user;
    }

    public void logout() {
        editor.clear();
        editor.commit();
    }
}
