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

    @Before
    public void setUpInteractions() {
        this.bean.setDelegate(this.delegate);
        this.bean.setGenerator(this.generator);
        this.bean.setSetter(this.setter);
        when(this.generator.get()).thenReturn(this.key);
    }

    @After
    public void verifyInteractions() {
        verifyNoMoreInteractions(this.delegate, this.generator, this.setter);
    }

    @Test
    public void testGetDelegate() {
        assertSame(this.delegate, this.bean.getDelegate());
    }

    @Test
    public void testGetGenerator() {
        assertSame(this.generator, this.bean.getGenerator());
    }

    @Test
    public void testGetSetter() {
        assertSame(this.setter, this.bean.getSetter());
    }

    @Test
    public void testCreateMany() {
        final Map<Object, Object> ret = this.bean.apply(Collections.singleton(this.value));
        assertSame(this.key, ret.get(this.value));
        verify(this.generator).get();
        verify(this.setter).accept(this.value, this.key);
        final Set<Object> values = Collections.singleton(this.value);
        verify(this.delegate).accept(values);
    }

}
