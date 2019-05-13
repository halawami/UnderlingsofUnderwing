package underlings.field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import underlings.gui.DrawChoice;

public class RemoveTests extends FieldTests {

    @Test
    public void testStart() {
        this.field.addHandler(0, this.handler);

        this.field.removeHandler(this.handler);

        assertEquals(1, this.handler.drawChoices.size());
        assertTrue(this.handler.drawChoices.contains(DrawChoice.RANDOM));
        assertNull(this.field.findHandler(this.handler));
    }

}
