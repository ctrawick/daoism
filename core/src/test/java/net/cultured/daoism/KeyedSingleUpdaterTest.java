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
 * Verify the implementation of {@link KeyedSingleUpdater}.
 *
 * @author ctrawick
 */
public class KeyedSingleUpdaterTest {

    private final KeyedSingleUpdater<Object, Object> bean = mock(KeyedSingleUpdater.class);
    private final Object key = new Object();
    private final Object data = new Object();

    /**
     * Set up the test interactions. This test assures that the implementation
     * method is executed when the mock object method is called.
     */
    @Before
    public void setUpInteractions() {
        doCallRealMethod().when(this.bean).accept(this.key, this.data);
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
     * Verify the implementation of
     * {@link KeyedSingleUpdater#accept(Object, Object)}. The implementation is
     * required to call
     * {@link KeyedSingleUpdater#updateOneByKey(Object, Object)}.
     */
    @Test
    public void testConsumerReturnsNormally() {
        // Execute the test
        this.bean.accept(this.key, this.data);

        // Verify that the correct methods are called.
        verify(this.bean).accept(this.key, this.data);
        verify(this.bean).updateOneByKey(same(this.key), same(this.data));
    }

}
