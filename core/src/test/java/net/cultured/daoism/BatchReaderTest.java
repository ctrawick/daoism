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
 * Verifies the implementation of {@link BatchReader}.
 *
 * @author ctrawick
 */
public class BatchReaderTest {

    private final BatchReader<Object, Object> bean = mock(BatchReader.class);
    private final Collection<Object> keys = mock(Collection.class);
    private final Map<Object, Object> data = mock(Map.class);

    /**
     * Set up the test interactions. This test assures that the implementation
     * method is executed when the mock object method is called.
     */
    @Before
    public void setUpInteractions() {
        when(this.bean.apply(this.keys)).thenCallRealMethod();
        when(this.bean.readMany(this.keys)).thenReturn(this.data);
        when(this.bean.readMany(this.keys)).thenReturn(this.data);
    }

    /**
     * Verify the test interactions. This test verifies that no unexpected
     * interactions occur on the mock delegate.
     */
    @After
    public void verifyInteractions() {
        verifyNoMoreInteractions(this.bean, this.keys, this.data);
    }

    /**
     * Verify the implementation of {@link BatchReader#apply(Collection)}. The
     * implementation is required to call
     * {@link BatchReader#readMany(Collection)}.
     */
    @Test
    public void testApply() {
        // Execute the test and verify the result
        final Map<Object, Object> actual = this.bean.apply(this.keys);
        assertSame(this.data, actual);

        // Verify that the correct methods are called.
        verify(this.bean).apply(this.keys);
        verify(this.bean).readMany(same(this.keys));
    }

}
