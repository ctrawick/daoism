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
 * Verifies the implementation of {@link BatchCreator}.
 *
 * @author ctrawick
 */
public class BatchDeleterTest {

    @Mock
    private BatchDeleter<Object> bean;
    @Mock
    private Collection<Object> keys;

    /**
     * Set up the test interactions. This test assures that the implementation
     * method is executed when the mock object method is called.
     */
    @Before
    public void setUpInteractions() {
        MockitoAnnotations.initMocks(this);
        doCallRealMethod().when(this.bean).accept(this.keys);
    }

    /**
     * Verify the test interactions. This test verifies that no unexpected
     * interactions occur on the mock delegate.
     */
    @After
    public void verifyInteractions() {
        verifyNoMoreInteractions(this.bean, this.keys);
    }

    /**
     * Verify the implementation of {@link BatchDeleter#accept(Collection)}. The
     * implementation is required to call
     * {@link BatchDeleter#deleteMany(Collection)}.
     */
    @Test
    public void testAccept() {
        // Execute the test
        this.bean.accept(this.keys);

        // Verify that the correct methods are called.
        verify(this.bean).accept(this.keys);
        verify(this.bean).deleteMany(same(this.keys));
    }

}
