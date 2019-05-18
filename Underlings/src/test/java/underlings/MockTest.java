package underlings;

import java.util.ArrayList;
import java.util.List;
import org.easymock.EasyMock;
import org.junit.After;

public abstract class MockTest {

    protected List<Object> mocks = new ArrayList<>();

    public <T> T mock(Class<T> mockClass) {
        T mock = EasyMock.mock(mockClass);
        this.mocks.add(mock);
        return mock;
    }

    public void replay() {
        EasyMock.replay(this.mocks.toArray());
    }

    @After
    public void verify() {
        EasyMock.verify(this.mocks.toArray());
    }

}
