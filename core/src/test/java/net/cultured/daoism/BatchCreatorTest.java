package net.cultured.daoism;

import static org.mockito.Matchers.same;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Verifies the implementation of {@link BatchCreator}.
 *
 * @author chris
 */
public class BatchCreatorTest {

    private final BatchCreator<Object> bean = mock(BatchCreator.class);
    private final Collection<Object> data = mock(Collection.class);

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
     * interactions occur on the mock data.
     */
    @After
    public void verifyInteractions() {
        verifyNoMoreInteractions(this.bean, this.data);
    }

    /**
     * Verify the implementation of {@link BatchCreator#accept(Collection)}. The
     * implementation is required to call
     * {@link BatchCreator#createMany(Collection)}.
     */
    @Test
    public void testAccept() {
        // Execute the test
        this.bean.accept(this.data);

        // Verify that the correct methods are called.
        verify(this.bean).accept(this.data);
        verify(this.bean).createMany(same(this.data));
    }

}
