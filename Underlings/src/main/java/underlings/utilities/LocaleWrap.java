package underlings.utilities;

import java.util.ResourceBundle;

public class LocaleWrap {

    public static String get(String string) {
        return ResourceBundle.getBundle("messages").getString(string);
    }

}
