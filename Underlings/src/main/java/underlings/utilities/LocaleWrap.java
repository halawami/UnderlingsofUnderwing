package underlings.utilities;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleWrap {

    public static Locale locale = Locale.ENGLISH;

    public static String get(String string) {
        return ResourceBundle.getBundle("messages", locale).getString(string);
    }
}
