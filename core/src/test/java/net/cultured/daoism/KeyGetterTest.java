package net.cultured.daoism;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class KeyGetterTest {

    @SuppressWarnings("unchecked")
    private final KeyGetter<Object, Object> bean = mock(KeyGetter.class);
    private final Object key = new Object();
    private final Object data = new Object();

    @Before
    public void setUpInteractions() {
        when(this.bean.apply(this.data)).thenCallRealMethod();
        when(this.bean.getKey(this.data)).thenReturn(this.key);
    }

    @After
    public void verifyInteractions() {
        verify(this.bean).apply(this.data);
        verify(this.bean).getKey(this.data);
        verifyNoMoreInteractions(this.bean);
    }

    @Test
    public void testFunctionReturnsNormally() throws Exception {
        final Object actual = this.bean.apply(this.data);
        assertSame(this.key, actual);
    }

}
