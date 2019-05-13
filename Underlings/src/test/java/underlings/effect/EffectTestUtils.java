package underlings.effect;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;

public class EffectTestUtils {

    public static <T> List<T> getMockObjects(Class<T> objectsClass, int numberOfObjects) {
        List<T> mockedObjects = new ArrayList<>();
        for (int i = 0; i < numberOfObjects; i++) {
            mockedObjects.add(EasyMock.mock(objectsClass));
        }
        return mockedObjects;
    }

}
