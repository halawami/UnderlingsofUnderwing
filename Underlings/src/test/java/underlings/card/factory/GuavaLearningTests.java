package underlings.card.factory;

import static org.junit.Assert.assertEquals;

import com.google.common.reflect.ClassPath;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class GuavaLearningTests {

	@Test
	public void testLoadClassFromPackage() throws Exception {
		List<Class<?>> retrievedClasses = new ArrayList<>();
		final ClassLoader loader = Thread.currentThread().getContextClassLoader();

		for (ClassPath.ClassInfo info : ClassPath.from(loader)
				.getTopLevelClassesRecursive("underlings.card.factory.samples")) {
			retrievedClasses.add(info.load());
		}

		assertEquals(2, retrievedClasses.size());
		assertEquals("TestClassOne", retrievedClasses.get(0).getSimpleName());
		retrievedClasses.get(0).newInstance();
		assertEquals("TestClassTwo", retrievedClasses.get(1).getSimpleName());
		retrievedClasses.get(1).newInstance();
	}
}
