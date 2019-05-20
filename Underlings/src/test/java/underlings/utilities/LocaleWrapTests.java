package underlings.utilities;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.junit.Test;

public class LocaleWrapTests {

    @Test
    public void testSetLocale() {
        LocaleUtilities localeWrap = new LocaleUtilities();
        localeWrap.setLocale(Locale.CANADA);

        assertEquals(Locale.CANADA, LocaleUtilities.locale);
    }

}
