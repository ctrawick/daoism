package net.cultured.daoism;

import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SingleReaderTest {

    @SuppressWarnings("unchecked")
    private final SingleReader<Object, Object> bean = mock(SingleReader.class);
    private final Object key = new Object();
    private final Object data = new Object();

    @Before
    public void setUpInteractions() {
        when(this.bean.apply(this.key)).thenCallRealMethod();
        when(this.bean.readOne(this.key)).thenReturn(this.data);
    }

    @After
    public void verifyInteractions() {
        verify(this.bean).apply(this.key);
        verify(this.bean).readOne(same(this.key));
        verifyNoMoreInteractions(this.bean);
    }

    @Test
    public void testFunctionReturnsNormally() throws Exception {
        final Object actual = this.bean.apply(this.key);
        assertSame(this.data, actual);
    }

}
