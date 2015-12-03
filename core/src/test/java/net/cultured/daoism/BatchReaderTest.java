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

public class BatchReaderTest {

    @SuppressWarnings("unchecked")
    private final BatchReader<Object, Object> bean = mock(BatchReader.class);
    @SuppressWarnings("unchecked")
    private final Collection<Object> keys = mock(Collection.class);
    @SuppressWarnings("unchecked")
    private final Map<Object, Object> data = mock(Map.class);

    @Before
    public void setUpInteractions() {
        when(this.bean.apply(this.keys)).thenCallRealMethod();
        when(this.bean.readMany(this.keys)).thenReturn(this.data);
        when(this.bean.readMany(this.keys)).thenReturn(this.data);
    }

    @After
    public void verifyInteractions() {
        verify(this.bean).apply(this.keys);
        verify(this.bean).readMany(same(this.keys));
        verifyNoMoreInteractions(this.bean, this.keys, this.data);
    }

    @Test
    public void testFunctionReturnsNormally() throws Exception {
        final Map<Object, Object> actual = this.bean.apply(this.keys);
        assertSame(this.data, actual);
    }

}
