package net.cultured.daoism;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Verifies the implementation of {@link GeneratedKeyBatchCreatorAdapter}.
 *
 * @author chris
 */
public class GeneratedKeyBatchCreatorAdapterTest {

    private final GeneratedKeyBatchCreatorAdapter<Object, Object> bean = new GeneratedKeyBatchCreatorAdapter<>();
    @SuppressWarnings("unchecked")
    private final Consumer<Collection<Object>> delegate = mock(Consumer.class);
    @SuppressWarnings("unchecked")
    private final Supplier<Object> generator = mock(Supplier.class);
    @SuppressWarnings("unchecked")
    private final BiConsumer<Object, Object> setter = mock(BiConsumer.class);
    private final Object key = new Object();
    private final Object value = new Object();

    /**
     * Set up the test interactions. This test sets the delegate to a mock
     * object and configures the delegate, generator, and setter to operate
     * correctly.
     */
    @Before
    public void setUpInteractions() {
        this.bean.setDelegate(this.delegate);
        this.bean.setGenerator(this.generator);
        this.bean.setSetter(this.setter);
        when(this.generator.get()).thenReturn(this.key);
    }

    /**
     * Verify the test interactions. This test verifies that no unexpected
     * interactions occur on the mock objects.
     */
    @After
    public void verifyInteractions() {
        verifyNoMoreInteractions(this.delegate, this.generator, this.setter);
    }

    /**
     * Verify the implementation of
     * {@link GeneratedKeyBatchCreatorAdapter#getDelegate()}. The implementation
     * is required to return the mock delegate set by
     * {@link #setUpInteractions()}.
     */
    @Test
    public void testGetDelegate() {
        assertSame(this.delegate, this.bean.getDelegate());
    }

    /**
     * Verify the implementation of
     * {@link GeneratedKeyBatchCreatorAdapter#getGenerator()}. The
     * implementation is required to return the mock delegate set by
     * {@link #setUpInteractions()}.
     */
    @Test
    public void testGetGenerator() {
        assertSame(this.generator, this.bean.getGenerator());
    }

    /**
     * Verify the implementation of
     * {@link GeneratedKeyBatchCreatorAdapter#getSetter()}. The implementation
     * is required to return the mock delegate set by
     * {@link #setUpInteractions()}.
     */
    @Test
    public void testGetSetter() {
        assertSame(this.setter, this.bean.getSetter());
    }

    /**
     * Verify the implementation of
     * {@link GeneratedKeyBatchCreatorAdapter#createMany(Collection)}. The
     * implementation is required to generate and set a key for each of the
     * input data elements, call the delegate with the input data, and return a
     * map of the data to the generated keys.
     */
    @Test
    public void testCreateMany() {
        // Execute the test and verify the result
        final Set<Object> values = Collections.singleton(this.value);
        final Map<Object, Object> ret = this.bean.createMany(values);
        assertSame(this.key, ret.get(this.value));

        // Verify that the generator and setter is called for each of the input
        // data elements and that the delegate is called for the batch.
        verify(this.generator).get();
        verify(this.setter).accept(this.value, this.key);
        verify(this.delegate).accept(values);
    }

}
