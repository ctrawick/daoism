package net.cultured.daoism;

import static org.mockito.Matchers.same;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Verify the implementation of {@link SingleCreator}.
 *
 * @author ctrawick
 */
public class SingleCreatorTest {

    @Mock
    private final SingleCreator<Object> bean = mock(SingleCreator.class);
    private final Object data = new Object();

    /**
     * Set up the test interactions. This test assures that the implementation
     * method is executed when the mock object method is called.
     */
    @Before
    public void setUpInteractions() {
        MockitoAnnotations.initMocks(this);
        doCallRealMethod().when(this.bean).accept(this.data);
    }

    /**
     * Verify the test interactions. This test verifies that no unexpected
     * interactions occur on the mock delegate.
     */
    @After
    public void verifyInteractions() {
        verifyNoMoreInteractions(this.bean);
    }

    /**
     * Verify the implementation of {@link SingleCreator#accept(Object)}. The
     * implementation is required to call
     * {@link SingleCreator#createOne(Object)}.
     */
    @Test
    public void testConsumerReturnsNormally() {
        // Execute the test
        this.bean.accept(this.data);

        // Verify that the correct methods are called.
        verify(this.bean).accept(this.data);
        verify(this.bean).createOne(same(this.data));
    }
}
