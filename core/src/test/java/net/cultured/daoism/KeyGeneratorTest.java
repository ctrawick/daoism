package net.cultured.daoism;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class KeyGeneratorTest {

    @SuppressWarnings("unchecked")
    private final KeyGenerator<Object> bean = mock(KeyGenerator.class);
    private final Object key = new Object();

    @Before
    public void setUpInteractions() {
        when(this.bean.get()).thenCallRealMethod();
        when(this.bean.generateKey()).thenReturn(this.key);
    }

    @After
    public void verifyInteractions() {
        verify(this.bean).get();
        verify(this.bean).generateKey();
        verifyNoMoreInteractions(this.bean);
    }

    @Test
    public void testFunctionReturnsNormally() throws Exception {
        final Object actual = this.bean.get();
        assertSame(this.key, actual);
    }

}
