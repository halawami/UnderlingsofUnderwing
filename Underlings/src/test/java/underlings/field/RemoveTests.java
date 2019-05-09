package underlings.field;

import static org.junit.Assert.assertNull;

import org.junit.Test;

public class RemoveTests extends FieldTests {

	@Test
	public void testStart() {
		this.field.addHandler(0, this.handler);

		this.field.removeHandler(this.handler);
		assertNull(this.field.findHandler(this.handler));
	}

}
