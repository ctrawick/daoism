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

public class BatchReaderAdapterTest {

    private final BatchReaderAdapter<Object, Object> bean = new BatchReaderAdapter<>();
    @SuppressWarnings("unchecked")
    private final Function<Collection<Object>, Map<Object, Object>> delegate = mock(Function.class);
    @SuppressWarnings("unchecked")
    private final Map<Object, Object> ret = mock(Map.class);
    private final Object key = new Object();
    private final Object value = new Object();

    @Before
    public void setUpInteractions() {
        this.bean.setDelegate(this.delegate);
        when(this.delegate.apply(Collections.singleton(this.key))).thenReturn(this.ret);
        when(this.ret.get(this.key)).thenReturn(this.value);
    }

    @After
    public void verifyInteractions() {
        verifyNoMoreInteractions(this.delegate, this.ret);
    }

    @Test
    public void testGetDelegate() {
        assertSame(this.delegate, this.bean.getDelegate());
    }

    @Test
    public void testCreateOne() {
        final Object actual = this.bean.apply(this.key);
        assertSame(this.value, actual);
        verify(this.delegate).apply(Collections.singleton(this.key));
        verify(this.ret).get(this.key);
    }

}
