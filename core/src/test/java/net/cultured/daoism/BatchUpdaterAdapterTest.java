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

public class BatchUpdaterAdapterTest {

    private final BatchUpdaterAdapter<Object> bean = new BatchUpdaterAdapter<>();
    @SuppressWarnings("unchecked")
    private final Consumer<Collection<Object>> delegate = mock(Consumer.class);
    private final Object data = new Object();

    @Before
    public void setUpInteractions() {
        this.bean.setDelegate(this.delegate);
    }

    @After
    public void verifyInteractions() {
        verifyNoMoreInteractions(this.delegate);
    }

    @Test
    public void testGetDelegate() {
        assertSame(this.delegate, this.bean.getDelegate());
    }

    @Test
    public void testCreateOne() {
        this.bean.accept(this.data);
        @SuppressWarnings("unchecked")
        final ArgumentCaptor<Collection<Object>> captor = ArgumentCaptor.forClass(Collection.class);
        verify(this.delegate).accept(captor.capture());
        final Collection<Object> c = captor.getValue();
        assertEquals(1, c.size());
        assertSame(this.data, c.iterator().next());
    }

}
