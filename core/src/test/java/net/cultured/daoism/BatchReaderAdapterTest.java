package net.cultured.daoism;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Verifies the implementation of {@link BatchReader}.
 *
 * @author chris
 */
public class BatchReaderAdapterTest {

    private final BatchReaderAdapter<Object, Object> bean = new BatchReaderAdapter<>();
    @SuppressWarnings("unchecked")
    private final Function<Collection<Object>, Map<Object, Object>> delegate = mock(Function.class);
    @SuppressWarnings("unchecked")
    private final Map<Object, Object> ret = mock(Map.class);
    private final Object key = new Object();
    private final Object value = new Object();

    /**
     * Set up the test interactions. This test sets the delegate to a mock
     * object and configures the delegate to operate correctly.
     */
    @Before
    public void setUpInteractions() {
        this.bean.setDelegate(this.delegate);
        when(this.delegate.apply(Collections.singleton(this.key))).thenReturn(this.ret);
        when(this.ret.get(this.key)).thenReturn(this.value);
    }

    /**
     * Verify the test interactions. This test verifies that no unexpected
     * interactions occur on the mock objects.
     */
    @After
    public void verifyInteractions() {
        verifyNoMoreInteractions(this.delegate, this.ret);
    }

    /**
     * Verify the implementation of {@link BatchReaderAdapter#getDelegate()}.
     * The implementation is required to return the mock delegate set by
     * {@link #setUpInteractions()}.
     */
    @Test
    public void testGetDelegate() {
        assertSame(this.delegate, this.bean.getDelegate());
    }

    /**
     * Verify the implementation of {@link BatchReaderAdapter#readOne(Object)}.
     * The implementation is required to wrap the input data in a singleton
     * collection, call the mock batch delegate, and return the correct data
     * from the map.
     */
    @Test
    public void testReadOne() {
        // Execute the test and verify the result
        final Object actual = this.bean.readOne(this.key);
        assertSame(this.value, actual);

        // Verify that the delegate is called with a singleton collection of the
        // input data and that the returned value is fetched from the result
        // map.
        verify(this.delegate).apply(Collections.singleton(this.key));
        verify(this.ret).get(this.key);
    }

}
