package net.cultured.daoism;

import static org.mockito.Matchers.same;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Verifies the implementation of {@link BatchUpdater}.
 *
 * @author ctrawick
 */
public class BatchUpdaterTest {

    @Mock
    private BatchUpdater<Object> bean;
    @Mock
    private Collection<Object> data;

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
        verifyNoMoreInteractions(this.bean, this.data);
    }

    /**
     * Verify the implementation of {@link BatchUpdater#accept(Collection)}. The
     * implementation is required to call
     * {@link BatchUpdater#updateMany(Collection)}.
     */
    @Test
    public void testAccept() {
        // Execute the test
        this.bean.accept(this.data);

        // Verify that the correct methods are called.
        verify(this.bean).accept(this.data);
        verify(this.bean).updateMany(same(this.data));
    }

}
