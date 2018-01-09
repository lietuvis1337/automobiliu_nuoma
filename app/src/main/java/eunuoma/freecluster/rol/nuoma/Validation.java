package eunuoma.freecluster.rol.nuoma;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by moksleivis on 2018-01-09.
 */

public class Validation {
    public static boolean isValid(String credentials) {
        final String CREDENTIALS_PATTERN = "^[0-9a-zA-Z]{3,15}$";
        Pattern pattern = Pattern.compile(CREDENTIALS_PATTERN);

        Matcher matcher = pattern.matcher (credentials);
        return matcher.matches();
    }
    public static boolean isValidEmail(String credentials) {
        final String CREDENTIALS_PATTERN = "^\\D.+@.+\\.[a-z]+";
        Pattern pattern = Pattern.compile(CREDENTIALS_PATTERN);

        Matcher matcher = pattern.matcher (credentials);
        return matcher.matches();
    }
}