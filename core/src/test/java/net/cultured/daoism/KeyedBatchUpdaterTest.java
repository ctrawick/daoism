package net.cultured.daoism;

import static org.mockito.Matchers.same;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Verify the implementation of {@link KeyedBatchUpdater}.
 *
 * @author ctrawick
 */
public class KeyedBatchUpdaterTest {

    private final KeyedBatchUpdater<Object, Object> bean = mock(KeyedBatchUpdater.class);
    private final Map<Object, Object> data = mock(Map.class);

    /**
     * Set up the test interactions. This test assures that the implementation
     * method is executed when the mock object method is called.
     */
    @Before
    public void setUpInteractions() {
        doCallRealMethod().when(this.bean).accept(this.data);
    }

    /**
     * Verify the test interactions. This test verifies that no unexpected
     * interactions occur on the mock delegate.
     */
    @After
    public void verifyInteractions() {
        verifyNoMoreInteractions(this.bean, this.data);
    }

    /**
     * Verify the implementation of {@link KeyedBatchUpdater#accept(Map)}. The
     * implementation is required to call
     * {@link KeyedBatchUpdater#updateManyByKey(Map)}.
     */
    @Test
    public void testConsumerReturnsNormally() {
        // Execute the test
        this.bean.accept(this.data);

        // Verify that the correct methods are called.
        verify(this.bean).accept(this.data);
        verify(this.bean).updateManyByKey(same(this.data));
    }

}
