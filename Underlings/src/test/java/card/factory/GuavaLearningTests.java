package card.factory;

import static org.junit.Assert.assertEquals;

import com.google.common.reflect.ClassPath;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class GuavaLearningTests {

    @Test
    public void testLoadClassFromPackage() throws IOException, ClassNotFoundException {
        List<Class<?>> retrievedClasses = new ArrayList<>();
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();

        for (ClassPath.ClassInfo info : ClassPath.from(loader)
                .getTopLevelClassesRecursive("tests.card.factory.samples")) {
            retrievedClasses.add(info.load());
        }

        assertEquals(2, retrievedClasses.size());
        assertEquals("TestClassOne", retrievedClasses.get(0).getSimpleName());
        assertEquals("TestClassTwo", retrievedClasses.get(1).getSimpleName());
    }
}
