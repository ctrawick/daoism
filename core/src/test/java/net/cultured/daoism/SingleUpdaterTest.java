package net.cultured.daoism;

import static org.mockito.Matchers.same;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Verify the implementation of {@link SingleUpdater}.
 *
 * @author ctrawick
 */
public class SingleUpdaterTest {

    private final SingleUpdater<Object> bean = mock(SingleUpdater.class);
    private final Object key = new Object();

    /**
     * Set up the test interactions. This test assures that the implementation
     * method is executed when the mock object method is called.
     */
    @Before
    public void setUpInteractions() {
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
     * Verify the implementation of {@link SingleUpdater#accept(Object)}. The
     * implementation is required to call
     * {@link SingleUpdater#updateOne(Object)}.
     */
    @Test
    public void testConsumerReturnsNormally() {
        // Execute the test
        this.bean.accept(this.key);

        // Verify that the correct methods are called.
        verify(this.bean).accept(this.key);
        verify(this.bean).updateOne(same(this.key));
    }

}
