package eunuoma.freecluster.rol.nuoma;

import android.content.Context;
import android.content.SharedPreferences;


public class User {

    private String username, password, email;
    private static final String PREFERENCES_FILE_NAME = "eunuoma.freecluster.rol.nuoma";
    private static final String USERNAME_KEY = "username";
    private static final String PASSWORD_KEY = "password";
    private static final String REMEMBER_ME_KEY = "rememberMe";

    private SharedPreferences sharedPrefs;

    // RegisterActivity

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // LoginActivity

    public User(Context context) {
        this.sharedPrefs = context.getSharedPreferences(User.PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
    }

    // Getteriai ir setteriai skirti naujo vartotojo registracijai

    public String getUsernameForRegistration() {
        return username;
    }

    public void setUsernameForRegistration(String username) {
        this.username = username;
    }

    public String getPasswordForRegistration() {
        return password;
    }

    public void setPasswordForRegistration(String password) {
        this.password = password;
    }

    public String getEmailForRegistration() {
        return email;
    }

    public void setEmailForRegistration(String email) {
        this.email = email;
    }

    public String getUsernameForLogin() {
        return this.sharedPrefs.getString(USERNAME_KEY, "");
    }

    public void setUsernameForLogin(String username) {
        this.sharedPrefs.edit().putString(USERNAME_KEY, username).commit();
    }

    public String getPasswordForLogin() {
        return this.sharedPrefs.getString(PASSWORD_KEY, "");
    }

    public void setPasswordForLogin(String password) {
        this.sharedPrefs.edit().putString(PASSWORD_KEY, password).commit();
    }

    public boolean isRemembered() {
        return this.sharedPrefs.getBoolean(REMEMBER_ME_KEY, false);
    }

    public void setRemembered(boolean remembered) {
        this.sharedPrefs.edit().putBoolean(REMEMBER_ME_KEY, remembered).commit();
    }

}
