package net.cultured.daoism;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.Collection;
import java.util.function.Consumer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

/**
 * Verifies the implementation of {@link BatchUpdaterAdapter}.
 *
 * @author chris
 */
public class BatchUpdaterAdapterTest {

    private final BatchUpdaterAdapter<Object> bean = new BatchUpdaterAdapter<>();
    @SuppressWarnings("unchecked")
    private final Consumer<Collection<Object>> delegate = mock(Consumer.class);
    private final Object data = new Object();

    /**
     * Set up the test interactions. This test sets the delegate to a mock
     * object.
     */
    @Before
    public void setUpInteractions() {
        this.bean.setDelegate(this.delegate);
    }

    /**
     * Verify the test interactions. This test verifies that no unexpected
     * interactions occur on the mock delegate.
     */
    @After
    public void verifyInteractions() {
        verifyNoMoreInteractions(this.delegate);
    }

    /**
     * Verify the implementation of {@link BatchUpdaterAdapter#getDelegate()}.
     * The implementation is required to return the mock delegate set by
     * {@link #setUpInteractions()}.
     */
    @Test
    public void testGetDelegate() {
        assertSame(this.delegate, this.bean.getDelegate());
    }

    /**
     * Verify the implementation of
     * {@link BatchUpdaterAdapter#updateOne(Object)}. The implementation is
     * required to wrap the input data in a singleton collection and call the
     * mock batch delegate.
     */
    @Test
    public void testUpdateOne() {
        // Execute the test
        this.bean.updateOne(this.data);

        // Verify that the delegate is called with a singleton collection of the
        // input data.
        @SuppressWarnings("unchecked")
        final ArgumentCaptor<Collection<Object>> captor = ArgumentCaptor.forClass(Collection.class);
        verify(this.delegate).accept(captor.capture());
        final Collection<Object> c = captor.getValue();
        assertEquals(1, c.size());
        assertSame(this.data, c.iterator().next());
    }

}
