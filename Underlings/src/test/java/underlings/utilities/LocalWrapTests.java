package underlings.utilities;

import static org.junit.Assert.assertEquals;
import java.util.Locale;
import org.junit.Test;

public class LocalWrapTests {

    @Test
    public void testSetLocale() {
        LocaleWrap localeWrap = new LocaleWrap();
        localeWrap.setLocale(Locale.CANADA);

        assertEquals(Locale.CANADA, LocaleWrap.locale);
    }

}
