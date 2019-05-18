package underlings.gui;

import java.util.Arrays;
import java.util.List;

import underlings.utilities.LocaleWrap;

public enum YesNoChoice {
    YES, NO;

    public boolean booleanValue;

    static {
        YES.booleanValue = true;
        NO.booleanValue = false;
    }

    @Override
    public String toString() {
        return LocaleWrap.get(this.name());
    }

    public static List<YesNoChoice> getChoices() {
        return Arrays.asList(YES, NO);
    }

}
