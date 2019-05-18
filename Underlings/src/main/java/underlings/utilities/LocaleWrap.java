package underlings.utilities;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleWrap {

    protected static Locale locale = Locale.ENGLISH;

    public void setLocale(Locale locale) {
        LocaleWrap.locale = locale;
    }

    public static String get(String string) {
        return ResourceBundle.getBundle("messages", locale).getString(string);
    }

    public static String format(String string, Object... arguments) {
        return MessageFormat.format(LocaleWrap.get(string), arguments);
    }
}
