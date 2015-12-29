package net.cultured.daoism;

import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Verifies the implementation of {@link GeneratedKeyBatchCreator}.
 *
 * @author ctrawick
 */
public class GeneratedKeyBatchCreatorTest {

    private final GeneratedKeyBatchCreator<Object, Object> bean = mock(GeneratedKeyBatchCreator.class);
    private final Collection<Object> dataIn = mock(Collection.class);
    private final Map<Object, Object> dataOut = mock(Map.class);

    /**
     * Set up the test interactions. This test assures that the implementation
     * method is executed when the mock object method is called.
     */
    @Before
    public void setUpInteractions() {
        when(this.bean.apply(this.dataIn)).thenCallRealMethod();
        when(this.bean.createMany(this.dataIn)).thenReturn(this.dataOut);
    }

    /**
     * Verify the test interactions. This test verifies that no unexpected
     * interactions occur on the mock data.
     */
    @After
    public void verifyInteractions() {
        verifyNoMoreInteractions(this.bean, this.dataIn, this.dataOut);
    }

    /**
     * Verify the implementation of {@link BatchCreator#accept(Collection)}. The
     * implementation is required to call
     * {@link BatchCreator#createMany(Collection)}.
     */
    @Test
    public void testApply() {
        // Execute the test
        final Map<Object, Object> actual = this.bean.apply(this.dataIn);
        assertSame(this.dataOut, actual);

        // Verify that the correct methods are called.
        verify(this.bean).apply(this.dataIn);
        verify(this.bean).createMany(same(this.dataIn));
    }

}
