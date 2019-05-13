package underlings;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;

public class TestUtils<T> {

    private Class<T> objectsClass;

    public static <T> TestUtils<T> mockListOf(Class<T> objectsClass) {
        TestUtils<T> utilsContainingType = new TestUtils<>();
        utilsContainingType.objectsClass = objectsClass;
        return utilsContainingType;
    }

    public List<T> withLength(int length) {
        List<T> mockObjects = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            mockObjects.add(EasyMock.mock(this.objectsClass));
        }
        return mockObjects;
    }

}
