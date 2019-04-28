package tests.card.cardFactory;

import com.google.common.reflect.ClassPath;
import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class GuavaLearningTests {

    @Test
    public void testLoadClassFromPackage() throws IOException, ClassNotFoundException {
        List<Class<?>> retrievedClasses = new ArrayList<>();
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();

        for (ClassPath.ClassInfo info : ClassPath.from(loader)
                .getTopLevelClassesRecursive("tests.card.cardFactory.testClasses")) {
            // Class<?> retrievedClass = Class.forName(info.getName());
            retrievedClasses.add(info.load());
        }

        assertEquals(2, retrievedClasses.size());

        assertEquals("testClassOne", retrievedClasses.get(0).getSimpleName());
        assertEquals("testClassTwo", retrievedClasses.get(1).getSimpleName());


    }
}