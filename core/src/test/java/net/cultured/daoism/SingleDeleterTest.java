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
 * Verify the implementation of {@link SingleDeleter}.
 *
 * @author ctrawick
 */
public class SingleDeleterTest {

    @Mock
    private final SingleDeleter<Object> bean = mock(SingleDeleter.class);
    private final Object key = new Object();

    /**
     * Set up the test interactions. This test assures that the implementation
     * method is executed when the mock object method is called.
     */
    @Before
    public void setUpInteractions() {
        MockitoAnnotations.initMocks(this);
        doCallRealMethod().when(this.bean).accept(this.key);
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
     * Verify the implementation of {@link SingleDeleter#accept(Object)}. The
     * implementation is required to call
     * {@link SingleDeleter#deleteOne(Object)}.
     */
    @Test
    public void testConsumerReturnsNormally() {
        // Execute the test
        this.bean.accept(this.key);

        // Verify that the correct methods are called.
        verify(this.bean).accept(this.key);
        verify(this.bean).deleteOne(same(this.key));
    }

}
