package underlings.utilities;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleWrap {

    public static Locale locale = Locale.ENGLISH;

    public static String get(String string) {
        return ResourceBundle.getBundle("messages", locale).getString(string);
    }

    public static String format(String string, Object... arguments) {
        return MessageFormat.format(LocaleWrap.get(string), arguments);
    }
}
